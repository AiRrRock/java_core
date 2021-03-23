package ru.geekbrains.java.level_1.lesson_6;

public class Animal {
    private String name;
    private int age;


    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void run(int distance) {
        System.out.println(name + " ran " + distance + (distance > 1 ? " meters" : "meter"));
    }

    public void swim(int distance) {
        System.out.println(name + " swam " + distance + (distance > 1 ? " meters" : "meter"));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name = " + name + " \nage = " + age;
    }
}
