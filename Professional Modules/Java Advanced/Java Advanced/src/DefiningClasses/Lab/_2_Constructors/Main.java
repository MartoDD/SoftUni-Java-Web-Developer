package DefiningClasses.Lab._2_Constructors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new ArrayList<>();

        while (n-- > 0) {
            String[] data = scanner.nextLine().split("\\s+");

            String brand = data[0];
            if (data.length > 1) {
                String model = data[1];
                int horsePower = Integer.parseInt(data[2]);
                Car car = new Car(brand, model, horsePower);
                cars.add(car);
            } else {
                Car car = new Car(brand);
                cars.add(car);
            }
        }
        cars.forEach(car -> System.out.println(car.getInfo()));
    }
}
