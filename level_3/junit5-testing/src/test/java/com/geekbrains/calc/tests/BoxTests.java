package com.geekbrains.calc.tests;

import com.geekbrains.calc.Box;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BoxTests {
    public static Stream<Arguments> generateBoxes() {
        List<Arguments> out = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            out.add(Arguments.arguments(new Box(4, "White"), new Box(4, "Blue")));
        }
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("generateBoxes")
    public void demoTest(Box box1, Box box2) {
        Assertions.assertEquals(box1.getSize(), box2.getSize());
    }
}
