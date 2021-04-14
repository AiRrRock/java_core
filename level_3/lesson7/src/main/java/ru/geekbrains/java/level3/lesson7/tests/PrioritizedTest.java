package ru.geekbrains.java.level3.lesson7.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.java.level3.lesson7.annotations.AfterSuite;
import ru.geekbrains.java.level3.lesson7.annotations.BeforeSuite;
import ru.geekbrains.java.level3.lesson7.annotations.Priority;

public class PrioritizedTest {

    private String a, b, c, d;

    public PrioritizedTest() {
        System.out.println("Constructor");
    }

    @BeforeSuite
    private void prepare() {
        a = "a";
        b = "b";
        c = "c";
        d = "d";
        System.out.println("In before suite");
    }


    @Test
    @Priority(priority = -11)
    private void testLowerThanExpectedPriority() {
        Assertions.assertEquals(true, "a".equals(a));
        System.out.println("Lowest priority");
    }

    @Test
    @Priority(priority = 1)
    private void testLowPriority() {
        Assertions.assertEquals(true, "a".equals(a));
        System.out.println("Lowest priority");
    }

    @Test
    @Priority(priority = 2)
    private void testLowSamePriority() {
        Assertions.assertEquals(true, "b".equals(b));
        System.out.println("Same priority 1");
    }

    @Test
    @Priority(priority = 2)
    private void testLowSamePriorityTwo() {
        Assertions.assertEquals(false, "b".equals(c));
        System.out.println("Same priority 2");

    }


    @Test
    @Priority(priority = 5)
    private void testMediumPriority() {
        Assertions.assertEquals(true, "d".equals(d));
        System.out.println("Mid priority");

    }


    @Test
    @Priority(priority = 10)
    private void testHighPriority() {
        Assertions.assertEquals(true, "d".equals(d));
        System.out.println("High priority");
    }

    @Test
    @Priority(priority = 100)
    private void testHighestPriority() {
        Assertions.assertEquals(true, "d".equals(d));
        System.out.println("Higher tha expected priority");
    }

    @AfterSuite
    private void cleanup() {
        a = null;
        b = null;
        c = null;
        d = null;
        System.out.println("After suite cleanup");
    }

}
