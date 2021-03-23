package ru.geekbrains.java.level_1.lesson_7;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final String OUTPUT_FORMAT = "The cat %s is %s hungry";

    public static void main(String[] args) {
        Random random = new Random();

        ru.geekbrains.java.level_1.lesson_7.Plate plate = new Plate(100);
        int numberOfCats = 10;
        Cat[] cats = new Cat[numberOfCats];

        for (int i = 0; i < numberOfCats; i++) {
            cats[i] = new Cat("Cat_" + i, random.nextInt(30));
        }

        for (Cat cat : cats) {
            cat.eat(plate);
        }
        //
        System.out.println("First feeding attempt");
        Arrays.stream(cats)
                .forEach(c -> System.out.println(String.format(OUTPUT_FORMAT, c.getName(), (c.isHungry() ? "" : "NOT"))));

        // Add food and try feeding again. Now all cats will be full

        System.out.println("Refilling plate");
        plate.addFood(100);

        System.out.println("Second feeding attempt");
        Arrays.stream(cats)
                .forEach(c -> c.eat(plate));
        Arrays.stream(cats)
                .forEach(c -> System.out.println(String.format(OUTPUT_FORMAT, c.getName(), (c.isHungry() ? "" : "NOT"))));

    }
}
