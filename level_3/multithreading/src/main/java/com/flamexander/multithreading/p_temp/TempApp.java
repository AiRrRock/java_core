package com.flamexander.multithreading.p_temp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TempApp {
    private static Integer mon = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (mon) {
                System.out.println("1-start");
                mon++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1-end");
            }
        }).start();
        new Thread(() -> {
            synchronized (mon) {
                System.out.println("2-start");
                mon++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2-end");
            }
        }).start();
        new Thread(() -> {
            synchronized (mon) {
                System.out.println("3-start");
                mon++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3-end");
            }
        }).start();
    }
}
