package ru.geekbrains.java.level_1.lesson_2;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static final String DELIMITER = IntStream.range(1, 100).mapToObj(i -> "-").collect(Collectors.joining());

    public static void main(String[] args) {
        //Task one
        System.out.println("Task one");
        int firstArray[] = {0, 1, 1, 1, 0}; //Don't declare arrays like that, this is just for example
        invertArray(firstArray);
        System.out.println(Arrays.toString(firstArray));
        System.out.println(DELIMITER);

        //Task two
        System.out.println("Task two");
        int[] secondArray = new int[8];
        fillArray(secondArray);
        System.out.println(Arrays.toString(secondArray));
        System.out.println(DELIMITER);

        //Task three
        System.out.println("Task three");
        int[] thirdArray = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 2};
        changeArray(thirdArray);
        System.out.println(Arrays.toString(thirdArray));
        System.out.println(DELIMITER);

        //Task four
        System.out.println("Task four");
        int matrixSize = 3;
        int[][] identityMatrix = new int[matrixSize][matrixSize];
        fillDiagonal(identityMatrix);
        System.out.println(Arrays.deepToString(identityMatrix));
        System.out.println(DELIMITER);

        // Task five
        System.out.println("Task five");
        int[] fourthArray = createRandomArray(11);
        Pair pair = findMinMax(fourthArray);
        String output = "The min number is %d\nThe max number is %d\nArray is %s";
        System.out.println(String.format(output, pair.getMin(), pair.getMax(), Arrays.toString(fourthArray)));
        System.out.println(DELIMITER);

        //Task six
        System.out.println("Task six");
        int[] fifthArray = createRandomArray(4);
        printEquality(fifthArray);
        fifthArray = new int[]{1, 2, 3};
        printEquality(fifthArray);
        System.out.println(DELIMITER);

        //Task seven
        System.out.println("Task seven");
        int[] sixthArray = {1, 2, 3, 4};
        int shift = 5;
        String initalArrayFormat = "\nThe initial array %s\nis shifted %s by %d ";
        String shiftedArrayFormat = "The shifted array is %s\n";
        System.out.println(String.format(initalArrayFormat, Arrays.toString(sixthArray), "right", shift));
        primitiveCyclicShiftArray(sixthArray, shift);
        System.out.println(String.format(shiftedArrayFormat, Arrays.toString(sixthArray)));
        shift = -6;
        int[] seventhArray = {1, 2, 3, 4};
        System.out.println(String.format(initalArrayFormat, Arrays.toString(seventhArray), "left", shift));
        primitiveCyclicShiftArray(seventhArray, shift);
        System.out.println(String.format(shiftedArrayFormat, Arrays.toString(seventhArray)));
        System.out.println(DELIMITER);

    }

    // Task one
    private static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] ^= 1;
        }
    }

    //Task two
    private static void fillArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 3;
        }
    }

    //Task three
    private static void changeArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
    }

    //Task four
    private static void fillDiagonal(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            array[i][i] = 1;
        }
    }

    //Auxiliary method for task five
    private static int[] createRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.round(Math.random() * size);
        }
        return array;
    }

    //Task five
    private static Pair findMinMax(int[] array) {
        if (isNullOrEmpty(array)) {
            throw new RuntimeException("You were asked to provide a non-empty array");
        }
        int min = array[0];
        int max = min;
        for (int number : array) {
            min = min < number ? min : number;
            max = max > number ? max : number;
        }
        return new Pair(min, max);

    }

    //Auxiliary method for task six
    private static boolean isNullOrEmpty(int[] array) {
        return (array == null || array.length == 0);
    }

    //Task six
    private static boolean hasEqualSumOfParts(int[] array) {
        return getIndexForEqualParts(array) > 0;
    }

    /*Task six. The method is created this way for providing better output.
    (We can make method return boolean, but we will need to return true instead of i and false instead of -1.)
    */
    private static int getIndexForEqualParts(int[] array) {
        if (isNullOrEmpty(array)) {
            throw new RuntimeException("You were asked to provide a non-empty array");
        }
        for (int i = 0; i < array.length - 1; i++) {
            int sumLeft = Arrays.stream(Arrays.copyOf(array, i + 1)).reduce(0, Integer::sum);
            int sumRight = Arrays.stream(Arrays.copyOfRange(array, i + 1, array.length)).reduce(0, Integer::sum);
            if (sumLeft == sumRight) {
                return i;
            }
        }
        return -1;
    }

    //Task six pretty printing of results
    private static void printEquality(int[] array) {
        String output = "\nThe array %s\n%s equal sums of parts";
        String equalParts = "The equal parts are:\nleft - %s\nright -%s";
        if (hasEqualSumOfParts(array)) {
            System.out.println(String.format(output, Arrays.toString(array), "HAS"));
            int middle = getIndexForEqualParts(array) + 1;
            System.out.println(String.format(equalParts,
                    Arrays.toString(Arrays.copyOf(array, middle)),
                    Arrays.toString(Arrays.copyOfRange(array, middle, array.length))));
        } else {
            System.out.println(String.format(output, Arrays.toString(array), "DON'T HAVE"));
        }
    }

    //Task seven
    private static void primitiveCyclicShiftArray(int[] array, int shift) {
        // Used enum, because it is easier to digest, but the String could've been used instead.
        Direction direction = shift >= 0 ? Direction.RIGHT : Direction.LEFT;

        // If shift is bigger than array size than we can move by shift mod array.length
        // This happens due to the fact that shifting on array.length elements will return the same array
        shift = shift % array.length;

        // We already know the direction to move elements thus we can remove sign from int to have 1 for cycle.
        shift = shift < 0 ? -shift : shift;
        // Move array one by one n times.
        for (int i = 0; i < shift; i++) {
            shiftOnce(array, direction);
        }
    }

    //Auxiliary method for primitiveCyclicShiftArray. Shift array 1 element in the direction
    private static void shiftOnce(int[] array, Direction direction) {
        int tmp;
        switch (direction) {
            case LEFT:
                tmp = array[0];
                /* Equivalent to
                for (int i=0;i<array.length-1; i++){
                    array[i]=array[i+1];
                } But because System.arraycopy is optimized it is better to use it instead.
                Also IDE highlights this kind of stuff*/
                System.arraycopy(array, 1, array, 0, array.length - 1);
                array[array.length - 1] = tmp;
                break;
            case RIGHT:
                tmp = array[array.length - 1];
                /* Equivalent to
                for (int i = array.length - 1; i > 0; i--) {
                    array[i - 1] = array[i];
                }*/
                System.arraycopy(array, 0, array, 1, array.length - 1);
                array[0] = tmp;
                break;
        }
    }

    // Auxiliary class for returning minMax pair in task five.
    // We could have returned the int[] or a some Collection, but this is way easier to read.
    public static class Pair {
        private final int min;
        private final int max;

        Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }

        int getMin() {
            return min;
        }

        int getMax() {
            return max;
        }
    }

    // Auxiliary enumeration for task seven.
    // We could have used String instead, but this is much more readable and error prone
    public enum Direction {
        LEFT, RIGHT
    }
}
