package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.BeverageRepository;
import restaurant.repositories.interfaces.HealthFoodRepository;
import restaurant.repositories.interfaces.TableRepository;

public class ControllerImpl implements Controller {

    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoney;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
        this.totalMoney = 0;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {

        HealthyFood healthyFood = null;

        switch (type) {
            case "Salad":
                healthyFood = new Salad(name, price);
                break;
            case "VeganBiscuits":
                healthyFood = new VeganBiscuits(name, price);
        }
        if (healthyFood != null) {
            if (healthFoodRepository.foodByName(name) == null) {
                healthFoodRepository.add(healthyFood);
                return String.format(OutputMessages.FOOD_ADDED, name);
            } else {
                throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
            }
        }
        return null;
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverage = null;

        switch (type) {
            case "Fresh":
                beverage = new Fresh(name, counter, brand);
                break;
            case "Smoothie":
                beverage = new Smoothie(name, counter, brand);
                break;
        }
        if (beverage != null) {
            if (beverageRepository.beverageByName(name, brand) == null) {
                beverageRepository.add(beverage);
                return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);
            } else {
                throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
            }
        }
        return null;
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = null;
        switch (type) {
            case "InGarden":
                table = new InGarden(tableNumber, capacity);
                break;
            case "Indoors":
                table = new Indoors(tableNumber, capacity);
                break;
        }
        if (table != null) {
            if (tableRepository.byNumber(tableNumber) == null) {
                tableRepository.add(table);
                return String.format(OutputMessages.TABLE_ADDED, tableNumber);
            } else {
                throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
            }
        }
        return null;
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table table = tableRepository.getAllEntities().stream().filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople).findFirst().orElse(null);
        if (table == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
        tableRepository.getAllEntities().stream().filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople).findFirst().get().reserve(numberOfPeople);
        return String.format(OutputMessages.TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = tableRepository.getAllEntities().stream().filter(t -> t.getTableNumber() == tableNumber).findFirst().orElse(null);
        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood healthyFood = healthFoodRepository.getAllEntities().stream().filter(f -> f.getName().equals(healthyFoodName)).findFirst().orElse(null);
        if (healthyFood == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }
        tableRepository.getAllEntities().stream().filter(t -> t.getTableNumber() == tableNumber).findFirst().get().orderHealthy(healthyFood);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = tableRepository.getAllEntities().stream().filter(t->t.getTableNumber()==tableNumber).findFirst().orElse(null);
        if (table==null){
            return String.format(OutputMessages.WRONG_TABLE_NUMBER,tableNumber);
        }
        Beverages beverage=beverageRepository.getAllEntities().stream().filter(b->b.getName().equals(name) && b.getBrand().equals(brand)).findFirst().orElse(null);
        if (beverage==null){
            return String.format(OutputMessages.NON_EXISTENT_DRINK,name,brand);
        }

        tableRepository.getAllEntities().stream().filter(t->t.getTableNumber()==tableNumber).findFirst().get().orderBeverages(beverage);

        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL,name,tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        double bill=tableRepository.byNumber(tableNumber).bill();
        this.totalMoney+=bill;
        return String.format(OutputMessages.BILL,tableNumber,bill);
    }


    @Override
    public String totalMoney() {

        return String.format(OutputMessages.TOTAL_MONEY,totalMoney);
    }
}
