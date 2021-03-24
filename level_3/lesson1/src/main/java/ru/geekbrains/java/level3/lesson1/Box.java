package ru.geekbrains.java.level3.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    ArrayList<T> fruits;
    float fruitWeight;

    public Box(List<T> fruits, float fruitWeight) {
        this.fruits = new ArrayList<T>(fruits);
        this.fruitWeight = fruitWeight;
    }

    public Box(float fruitWeight) {
        this.fruits = new ArrayList<T>();
        this.fruitWeight = fruitWeight;
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        return fruits.size() * fruitWeight;
    }

    public void moveContentTo(Box<T> other) {
        other.addContent(this.fruits);
        this.fruits.clear();
    }

    public void addContent(ArrayList<T> newFruits) {
        fruits.addAll(newFruits);
    }

    public boolean compare(Box<? extends Fruit> other) {
        return Math.abs(other.getWeight() - this.getWeight()) < 0.001;
    }
}
