import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MainTest {
    public static Stream<Arguments> generateBoxes() {
        List<Arguments> out = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int[] array = {1, 2, 3, 4, 1, 2};
            int[] compare = {1, 2};
            out.add(Arguments.arguments(array, compare));
        }
        return out.stream();
    }

    public static Stream<Arguments> generateArrays() {
        List<Arguments> out = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int[] array = new int[i];

            for (int j = 0; j < i; j++) {
                array[j] = j;
            }
            out.add(Arguments.arguments(array, i >= 5));
        }
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("generateArrays")
    public void testArrayReturned(int[] array, boolean reference) {
        Assertions.assertEquals(Main.hasFoursAndOnes(array), reference);
    }


    @Test
    public void testOnlyFours() {
        int[] array = {4, 4, 4, 4, 4};
        Assertions.assertEquals(false, Main.hasFoursAndOnes(array));
    }


    @Test
    public void testOnlyOnce() {
        int[] array = {1, 1, 1, 1};
        Assertions.assertEquals(false, Main.hasFoursAndOnes(array));
    }

    @Test
    public void testNull() {
        Assertions.assertEquals(false, Main.hasFoursAndOnes(null));

    }


    @ParameterizedTest
    @MethodSource("generateBoxes")
    public void testArrayReturned(int[] array, int[] reference) {
        Assertions.assertArrayEquals(reference, Main.getSubArrayAfterLastFour(array));
    }

    @Test
    public void testExceptionThrown() {
        int[] array = {1, 2, 3, 1, 2};
        Assertions.assertThrows(RuntimeException.class, () -> Main.getSubArrayAfterLastFour(array));
    }

    @Test
    public void testEmptyArray() {
        int[] array = new int[0];
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.getSubArrayAfterLastFour(array);
        });
    }


    @Test
    public void testNullArray() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Main.getSubArrayAfterLastFour(null);
        });
    }

}
