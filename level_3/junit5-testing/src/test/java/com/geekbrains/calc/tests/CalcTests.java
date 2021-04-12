package com.geekbrains.calc.tests;

import com.geekbrains.calc.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CalcTests {
    private Calculator calculator;

    @BeforeEach
    public void init() {
        calculator = new Calculator();
    }

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

    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @ParameterizedTest
    public void testSomething(int n) {
        Assertions.assertEquals(n, n);
    }

    @ParameterizedTest
    @MethodSource("dataForASTest")
    public void addTestWithArgumentsStream(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.add(a, b));
    }

    public static Stream<Arguments> dataForASTest() {
        List<Arguments> out = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int a = (int) (Math.random() * 100);
            int b = (int) (Math.random() * 100);
            int result = a + b;
            out.add(Arguments.arguments(a, b, result));
        }
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("demoArgs")
    public void demoTest(int[] arr, int resultSum, boolean isEqual) {
        Assertions.assertEquals(isEqual, calculator.sum(arr) == resultSum);
    }

    public static Stream<Arguments> demoArgs() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[]{1, 2, 3, 4}, 11, true));
        out.add(Arguments.arguments(new int[]{1, 2, 3, 4}, 10, false));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 1, 1}, 5, true));
        return out.stream();
    }

    @Test
    public void assertAllTestDemo() {
        Assertions.assertAll(
                () -> {
                    Assertions.assertEquals(2, 2);
                }, () -> {
                    Assertions.assertEquals(2, 2);
                }, () -> {
                    Assertions.assertEquals(4, 4);
                }
        );
    }

    @Test
    public void subTest() {
        Assertions.assertEquals(0, calculator.sub(2, 2));
    }

    @Test
    public void mulTest() {
        Assertions.assertEquals(4, calculator.mul(2, 2));
    }

    @Test
    public void divTest() {
        Assertions.assertEquals(1, calculator.div(2, 2));
    }

    @Test
    public void divBy0Test() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.div(10, 0);
        });
    }
}
