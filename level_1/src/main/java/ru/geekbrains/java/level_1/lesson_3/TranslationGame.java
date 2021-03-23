package ru.geekbrains.java.level_1.lesson_3;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TranslationGame {
    private static final String QUESTION = "Can you translate %s into English?";
    private static final String CONGRATULATIONS = "Congratulations! You are correct the word %s is translated %s";
    private static Scanner scanner = new Scanner(System.in);
    private static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
            "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    private static String[] translations = {"яблоко", "апельсин", "лимон", "банан", "абрикос", "авокадо", "брокколи", "морковь",
            "вишня", "чеснок", "виногдад", "дыня", "лук-порей", "киви", "манго", "гриб",
            "орех", "оливка", "горох", "арахис", "груша", "перец", "ананас", "тыква", "картофель"};

    public static void main(String[] args) {
        translate();
        scanner.close();
    }

    public static void translate() {
        int randomNumber = new Random().nextInt(words.length);
        String word = words[randomNumber];
        String translation = translations[randomNumber];
        String maskedString = IntStream.range(0, 14).mapToObj(i -> "#").collect(Collectors.joining());


        while (true) {
            System.out.println(String.format(QUESTION, translation));
            String answer = scanner.nextLine();
            if (word.equals(answer)) {
                System.out.println(String.format(CONGRATULATIONS, translation, word));
                break;
            } else {
                maskedString = createMaskedString(answer, word, maskedString);
                System.out.println(maskedString);
            }
        }

    }

    private static String createMaskedString(String answer, String word, String maskedString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 15; i++) {

            if (maskedString.length() > i && maskedString.charAt(i) != '#') {
                stringBuilder.append(maskedString.charAt(i));
            } else if (word.length() > i && answer.length() > i) {
                if (word.charAt(i) == answer.charAt(i)) {
                    stringBuilder.append(word.charAt(i));
                } else {
                    stringBuilder.append("#");
                }
            } else {
                stringBuilder.append("#");
            }
        }
        return stringBuilder.toString();
    }


}
