package ru.geekbrains.java.level_1.lesson_6;


import java.util.Arrays;
import java.util.Random;

import static ru.geekbrains.java.level_1.lesson_6.AnimalCounter.countAnimal;

public class Main {
    private static Random random = new Random();
    private static final String OUTPUT_FORMAT = "There happened to be %d %s";

    public static void main(String[] args) {
        Animal[] animals = new Animal[10];
        for (int i = 0; i < animals.length; i++) {
            int type = random.nextInt(3);
            if (type > 1) {
                animals[i] = new Cat("Cat_" + i, i * 2);
            } else {
                animals[i] = new Dog("Dog_" + i, i * 2);
            }
        }
        System.out.println(Arrays.toString(animals));
        System.out.println(String.format(OUTPUT_FORMAT, countAnimal(animals, Dog.class), "dog(s)"));
        System.out.println(String.format(OUTPUT_FORMAT, countAnimal(animals, Cat.class), "cat(s)"));
        for (Animal animal : animals) {

            int runDistance = random.nextInt(1000);
            int swimDistance = random.nextInt(30);
            System.out.println("Making " + animal.getName() + " run");
            animal.run(runDistance);
            System.out.println("Making " + animal.getName() + " swim");
            animal.swim(swimDistance);
            System.out.println();
        }
    }

}
