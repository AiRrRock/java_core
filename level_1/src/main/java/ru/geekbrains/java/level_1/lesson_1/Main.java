package ru.geekbrains.java.level_1.lesson_1;

import java.util.Collections;

public class Main {
    /* All primitive types, belonging to the class. Created but not initialized.
    This variables have no purpose outside of the exercise thus named as a<variable type>*/
    private static byte aByte;
    private static short aShort;
    private static int anInt;
    private static long aLong;
    private static float aFloat;
    private static double aDouble;
    private static char aChar;
    private static boolean aBoolean;
    private static final String DELIMITER = String.join("", Collections.nCopies(100, "-"));
    private static final String GREETINGS_FORMAT = "Hello, %s!";
    private static final String POSITIVITY_FORMAT = "The number %d is %s";

    /* All primitive types, belonging to the instance. Created but not initialized.
    Because we are not going to create an instance this variables will be unavailable.
    This variables have no purpose outside of the exercise thus named as some<variable type>*/
    private byte someByte;
    private short someShort;
    private int someInt;
    private long someLong;
    private float someFloat;
    private double someDouble;
    private char someChar;
    private boolean someBoolean;

    //Initialization of static variables declared above. Part of second task
    static {
        aByte = 0b101; // Setting binary value to byte. The value is 1*2^2+1*2^0 = 5
        aShort = 0xef; // Setting hexadecimal value to short. The value is 14*16^1+15*16^0 = 239
        anInt = 076; // Setting octal value to int. The value is 7*8^1+6*8^0 = 62
        aLong = 1_000_000_000L; // Setting value to 1000000000 (_ signs are ignored by compiler)
        aFloat = 2.78e5f; // Setting float value to 2.78*10^5 = 278000
        aDouble = 0xff.1p3; // Setting the value to hex value. (15*16^1 + 15* 16^0 + 1 *16^(-1)) * 2^3 =  255.0625 * 8 = 2040.5
        aChar = '\u0068'; // Setting value to unicode character. the value is h
        aBoolean = true;

    }

    /* Initialization of variables declared above. Part of second task
    Because we are not going to create an instance this variables will be unavailable.
    This block will only be called if we create an instance*/ {
        someByte = -1;
        someShort = -1;
        someInt = -1;
        someLong = -1;
        someFloat = -1.0f;
        someDouble = -1.0;
        someChar = 'b';
        someBoolean = false;
    }


    //First task
    public static void main(String[] args) {
        // Print all static variable initialised in static block;
        System.out.println("Second task");
        System.out.println(aByte);
        System.out.println(aShort);
        System.out.println(anInt);
        System.out.println(aLong);
        System.out.println(aFloat);
        System.out.println(aDouble);
        System.out.println(aChar);
        System.out.println(aBoolean);
        System.out.println(DELIMITER);

        //Second task
        byte otherByte = 1;
        short otherShort = 1;
        int otherInt = 1;
        long otherLong = 1L;
        float otherFloat = 1.0f;
        double otherDouble = 1.0;
        char otherChar = 'a';
        boolean otherBoolean = Math.random() * 100 > 50;
        System.out.println(otherByte);
        System.out.println(otherShort);
        System.out.println(otherInt);
        System.out.println(otherLong);
        System.out.println(otherFloat);
        System.out.println(otherDouble);
        System.out.println(otherChar);
        System.out.println(otherBoolean);
        System.out.println(DELIMITER);

        System.out.println("Third task");
        System.out.println(calculate(2.5f, 3.1f, 4.7f, 13f));
        System.out.println(calculate(2f, 1f, 1f, 0f));
        System.out.println(DELIMITER);

        System.out.println("Fourth task");
        String outputFormat = "Sum of %d and %d is %s of 10 - 20 range ";
        for (int i = 0; i < 12; i += 4) {
            for (int j = 11; j > -1; j -= 5) {
                System.out.println(String.format(outputFormat, i, j, isSumInsideRange(i, j) ? "inside" : "outside"));
            }
        }
        System.out.println(DELIMITER);

        System.out.println("Fifth task");
        for (int i = -6; i < 7; i += 3) {
            isPositiveOrNegative(i);
        }
        System.out.println(DELIMITER);

        System.out.println("Sixth task");
        outputFormat = "The number %d is Negative = %b";
        for (int i = -6; i < 7; i += 3) {
            System.out.println(String.format(outputFormat, i, isNegative(i)));
        }
        System.out.println(DELIMITER);

        System.out.println("Seventh task");
        String[] names = {"John", "Jill", "Jsck", "Ann", "Kate"};
        for (String name : names) {
            greet(name);
        }
        System.out.println(DELIMITER);

        System.out.println("Eighth task");
        outputFormat = "The year %d is %sa leap year";
        for (int i = 1; i < 2000; i += 33) {
            System.out.println(String.format(outputFormat, i, isLeapYear(i) ? "" : "NOT "));
        }
        System.out.println(String.format(outputFormat, 2000, isLeapYear(2000) ? "" : "NOT "));
        System.out.println(String.format(outputFormat, 2020, isLeapYear(2020) ? "" : "NOT "));
        System.out.println(DELIMITER);
    }

    //Third task
    private static float calculate(float a, float b, float c, float d) {
        // Will not check for d == 0, because flaots can be divided by 0 , returning Infinity)
        return a * (b + c / d);
    }

    // Fourth task (the study guide asks to name this method task10and20 , but this is against naming conventions)
    private static boolean isSumInsideRange(int firstAddend, int secondAddend) {
        int sum = firstAddend + secondAddend;
        return sum >= 10 && sum <= 20;
    }

    // In case the method has to be named task10and20 here is the method
    private static boolean task10and20(int firstAddend, int secondAddend) {
        return isSumInsideRange(firstAddend, secondAddend);
    }

    // Fifth task (I would've called it differently - printSign)
    private static void isPositiveOrNegative(int number) {
        System.out.println(String.format(POSITIVITY_FORMAT, number, isNegative(number) ? "Negative" : "Positive"));
    }

    // Sixth task
    private static boolean isNegative(int number) {
        return number < 0;
    }

    //Seventh task (the study guide asks to name this method greetings , but this is against naming conventions)
    private static void greet(String name) {
        System.out.println(String.format(GREETINGS_FORMAT, name)); // System.out.println("Привет, " + name);
    }

    // In case the method has to be named greetings here is the method
    private static void greetings(String name){
        greet(name);
    }

    //Eighth task
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0);
    }
}
