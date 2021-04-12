package com.geekbrains.calc.tests;

import com.geekbrains.calc.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorTests {
    private Calculator calculator;

    @BeforeEach
    public void init() {
        calculator = new Calculator();
        System.out.println("init");
    }

    @AfterEach
    public void shutdown() {
        System.out.println("shutdown");
    }

    @Test
    public void testAdd1() {
        Assertions.assertEquals(4, calculator.add(2, 2));
    }

//    @Test
//    public void testAdd1() {
//        Assertions.assertEquals(4, calculator.add(2, 2));
//        Assertions.assertEquals(6, calculator.add(13, 3));
//        Assertions.assertEquals(8, calculator.add(4, 14));
//    }

    @CsvSource({
            "5, 5, 10",
            "1, 1, 2",
            "6, 7, 13",
            "10, 20, 30",
            "0, 10, 10"
    })
    @ParameterizedTest
    public void addTest(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.add(a, b));
    }

//    @CsvSource({
//            "5, 5, 10, true",
//            "1, 3, 2, false"
//    })
//    @ParameterizedTest
//    public void addTestHmmm(int a, int b, int result, boolean ok) {
//        Assertions.assertEquals(calculator.add(a, b) == result, ok);
//    }

    @Test
    public void testSub() {
        Assertions.assertEquals(10, calculator.sub(20, 10));
    }

    @Test
    public void testMul() {
        Assertions.assertEquals(10, calculator.mul(5, 2));
    }

    @Test
    public void testDiv() {
        Assertions.assertEquals(10, calculator.div(50, 5));
    }

    @Test
    public void testDivBy0() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.div(10, 0);
        });
    }
}
