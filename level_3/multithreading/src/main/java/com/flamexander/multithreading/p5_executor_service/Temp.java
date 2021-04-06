package com.flamexander.multithreading.p5_executor_service;

import java.util.concurrent.*;

public class Temp {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                System.out.println("1-A");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("1-B");
            }
        }).start();

        Thread.sleep(400);

        new Thread(() -> {
            synchronized (o) {
                System.out.println("2-A");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2-B");
            }
        }).start();

        Thread.sleep(400);

        new Thread(() -> {
            synchronized (o) {
                System.out.println("3-A");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("3-B");
            }
        }).start();

        Thread.sleep(600);
        synchronized (o) {
            o.notify();
        }
        Thread.sleep(600);
        synchronized (o) {
            o.notify();
        }
        Thread.sleep(600);
        synchronized (o) {
            o.notify();
        }
    }
}
