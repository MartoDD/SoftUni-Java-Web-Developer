package Polymorphism.Lab._3_Animals;

public class Cat extends Animal{

    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    protected String explainSelf() {
        return String.format("%s%nMEEOW",super.explainSelf());
    }
}
