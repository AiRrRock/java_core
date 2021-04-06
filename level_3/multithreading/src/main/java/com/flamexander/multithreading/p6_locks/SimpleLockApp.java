package com.flamexander.multithreading.p6_locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLockApp {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();

        new Thread(() -> {
            System.err.println("BEFORE-LOCK-1");
            try {
                lock.lock();
                System.err.println("GET-LOCK-1");
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.err.println("END-1");
                lock.unlock();
            }
        }).start();

//        new Thread(() -> {
//            System.err.println("BEFORE-LOCK-2");
//            try {
//                lock.lock();
//                System.err.println("GET-LOCK-2");
//                Thread.sleep(8000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                System.err.println("END-2");
//                lock.unlock();
//            }
//        }).start();

        new Thread(() -> {
            System.err.println("BEGIN-3");
            try {
                if (lock.tryLock(14L, TimeUnit.SECONDS)) {
                    try {
                        System.err.println("LOCK-SECTION-3");
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } finally {
                        lock.unlock();
                        System.err.println("END-3");
                    }
                } else {
                    System.err.println("Не очень-то и нужно было...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
