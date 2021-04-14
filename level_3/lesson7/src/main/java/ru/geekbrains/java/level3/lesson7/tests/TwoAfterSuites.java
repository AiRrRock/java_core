package ru.geekbrains.java.level3.lesson7.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.java.level3.lesson7.annotations.AfterSuite;

public class TwoAfterSuites {
    @AfterSuite
    private void a() {

    }

    @AfterSuite
    private void b() {

    }

    @Test
    public void test() {
        Assertions.assertEquals(true, true);
    }

}
