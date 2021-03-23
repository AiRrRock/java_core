package ru.geekbrains.java.level_1.lesson_7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void decreaseFood(int amount) {
        if (amount < food) {
            food -= amount;
        }

    }

    public boolean hasEnoughFood(int amount) {
        return food - amount >= 0;
    }

    public void addFood(int amount) {
        food += amount;
    }
}
