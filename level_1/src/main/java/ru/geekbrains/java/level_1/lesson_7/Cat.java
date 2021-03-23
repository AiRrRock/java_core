package ru.geekbrains.java.level_1.lesson_7;

public class Cat {
    private String name;
    private int appetite;
    private boolean hungry = true;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(ru.geekbrains.java.level_1.lesson_7.Plate plate) {
        if (hungry) {
            if (plate.hasEnoughFood(appetite)){
                plate.decreaseFood(appetite);
                hungry = false;
            }
        }
    }

    public boolean isHungry() {
        return hungry;
    }

    public String getName() {
        return name;
    }
}
