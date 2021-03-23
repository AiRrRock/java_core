package ru.geekbrains.java.level_1.lesson_3;


import java.util.Random;
import java.util.Scanner;

// First task
public class GuessingGame {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean replay;
        do {
            replay = guess();
        } while (replay);
        scanner.close();
    }

    private static boolean guess() {
        int correctAnswer = new Random().nextInt(10);
        for (int i = 0; i < 3; i++) {
            System.out.println("Please enter a number between 0 and 9");
            int answer = scanner.nextInt();
            if (answer == correctAnswer) {
                System.out.println("Congratulations! You have won the Guessing Game 1.0.0 !!!");
                break;
            } else if (i < 2) {
                System.out.println(String.format("The number is %s", answer < correctAnswer ? "bigger" : "smaller"));
            } else {
                System.out.println("The Guessing Game 1.0.0 is out of your league!");
            }
        }
        int replay;
        do {
            System.out.println("Play again?\n 1 - yes, 0 - no");
            replay = scanner.nextInt();
        } while (replay != 0 && replay != 1);
        return replay == 1;
    }
}
