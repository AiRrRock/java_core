package ru.geekbrains.java.level_1.lesson_6;

public class AnimalCounter<T extends Animal> {

    public static int countAnimal(Animal[] animals, Class<? extends Animal> type) {
        int count = 0;
        for (Animal animal : animals) {
            if (animal.getClass().equals(type)) {
                count++;
            }
        }
        return count;
    }
}
