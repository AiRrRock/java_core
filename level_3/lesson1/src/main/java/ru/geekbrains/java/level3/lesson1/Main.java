package ru.geekbrains.java.level3.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Part 1
        String[] strings = {"1", "2", "3", "4"};
        Integer[] ints = {1, 2, 3, 4};
        changeElements(ints, 0, 3);
        changeElements(strings, 0, 2);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(strings));

        //Part 2
        System.out.println(strings.getClass());
        System.out.println(asList(strings).getClass());

        //Part 3
        List<Apple> apples = Arrays.asList(new Apple(), new Apple(), new Apple());
        List<Orange> oranges = Arrays.asList(new Orange(), new Orange());
        Box<Apple> appleBox = new Box<Apple>(apples, 1f);
        Box<Orange> orangeBox = new Box<Orange>(oranges, 1.5f);
        Box<Orange> orangeBox2 = new Box<Orange>(oranges, 1.5f);
        System.out.println(orangeBox2.compare(orangeBox));
        System.out.println(orangeBox.compare(appleBox));
        System.out.println(orangeBox.getWeight());
        orangeBox2.moveContentTo(orangeBox);
        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox2.getWeight());
        System.out.println(orangeBox.compare(orangeBox2));
    }

    public static <T> void changeElements(T[] array, int firstPos, int secondPos) {
        int highestPos = firstPos > secondPos ? firstPos : secondPos;
        if (highestPos < array.length) {
            T elem = array[firstPos];
            array[firstPos] = array[secondPos];
            array[secondPos] = elem;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static <T> ArrayList<T> asList(T[] array) {
        // asList returns ArrayList
        return new ArrayList<T>(Arrays.asList(array));
        //equivalent to
        /*ArrayList<T> arrayList = new ArrayList<T>(array.length);
        arrayList.addAll(Arrays.asList(array));
        return arrayList;*/
    }
}
