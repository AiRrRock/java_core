package ru.geekbrains.java.level_1.lesson_6;

public class Dog extends Animal {
    private static final int MAX_RUN_DISTANCE = 500;
    private static final int MAX_SWIM_DISTANCE = 10;


    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void swim(int distance) {
        if (distance > MAX_RUN_DISTANCE) {
            super.run(MAX_RUN_DISTANCE);
            int extraDistamce = (distance - MAX_RUN_DISTANCE);
            System.out.println(super.getName() + "is exhausted and cannot swim for another " + extraDistamce +
                    (extraDistamce > 1 ? " meters" : " meter"));
        } else {
            super.run(distance);
        }
    }

    @Override
    public void run(int distance) {
        if (distance > MAX_RUN_DISTANCE) {
            super.run(MAX_RUN_DISTANCE);
            int extraDistamce = (distance - MAX_RUN_DISTANCE);
            System.out.println(super.getName() + "is exhausted and cannot run for another " + extraDistamce +
                    (extraDistamce > 1 ? " meters" : " meter"));
        } else {
            super.run(distance);
        }


    }

    @Override
    public String toString() {
        return "This is a dog\n\t" + super.toString();
    }


}
