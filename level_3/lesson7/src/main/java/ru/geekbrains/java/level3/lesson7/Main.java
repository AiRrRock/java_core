package ru.geekbrains.java.level3.lesson7;

import ru.geekbrains.java.level3.lesson7.tests.PrioritizedTest;
import ru.geekbrains.java.level3.lesson7.tests.SimpleTest;
import ru.geekbrains.java.level3.lesson7.tests.TwoAfterSuites;
import ru.geekbrains.java.level3.lesson7.tests.TwoBeforeSuites;

import java.lang.reflect.InvocationTargetException;

import static ru.geekbrains.java.level3.lesson7.test_runners.TestRunner.start;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("Starting tests by name");
        start("ru.geekbrains.java.level3.lesson7.tests.SimpleTest");
        start("ru.geekbrains.java.level3.lesson7.tests.PrioritizedTest");
        System.out.println("\n");
        System.out.println("Starting tests by class");
        start(PrioritizedTest.class);
        start(SimpleTest.class);
        System.out.println("\n");
        System.out.println("Starting test that will fail");
        try {
            start(TwoAfterSuites.class);
        }catch (Throwable t) {
            //Just catch the exception
            System.out.println(t.getLocalizedMessage());
        }

        try {
            start(TwoBeforeSuites.class);
        }catch (Throwable t) {
            //Just catch the exception
            System.out.println(t.getLocalizedMessage());
        }


    }
}
