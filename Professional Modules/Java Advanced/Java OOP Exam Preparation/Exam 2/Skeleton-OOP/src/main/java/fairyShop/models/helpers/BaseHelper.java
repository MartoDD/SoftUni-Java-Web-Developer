package fairyShop.models.helpers;

import fairyShop.common.ExceptionMessages;
import fairyShop.models.instrumets.Instrument;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseHelper implements Helper {

    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    public BaseHelper(String name, int energy) {
        setName(name);
        this.energy = energy;
        this.instruments = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void work() {
        setEnergy(Math.max(getEnergy() - 10, 0));
    }

    @Override
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return getEnergy() > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }
    public String getInfo(){
        return "hi";
    }
}
