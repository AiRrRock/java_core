package ru.geekbrains.java.level_1.lesson_6;

public class Cat extends Animal {
    private static final int MAX_RUN_DISTANCE = 200;


    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void swim(int distance) {
        System.out.println(super.getName() + " look at you resentfully and refuses to enter the water.");
    }

    @Override
    public void run(int distance) {
        if (distance > MAX_RUN_DISTANCE) {
            super.run(MAX_RUN_DISTANCE);
            System.out.println(super.getName() + " is very disappointed in you and refuses to run extra " + (distance - MAX_RUN_DISTANCE) + " m");
        } else {
            super.run(distance);
        }


    }

    @Override
    public String toString() {
        return "This is a cat\n\t" + super.toString();
    }
}
