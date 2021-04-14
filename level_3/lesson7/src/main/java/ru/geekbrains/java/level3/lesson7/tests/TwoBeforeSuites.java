package ru.geekbrains.java.level3.lesson7.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.java.level3.lesson7.annotations.BeforeSuite;

public class TwoBeforeSuites {
    @BeforeSuite
    private void a() {

    }

    @BeforeSuite
    private void b() {

    }

    @Test
    public void test() {
        Assertions.assertEquals(true, true);
    }
}
