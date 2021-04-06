package com.flamexander.multithreading.p1_thread_creation_and_base;

import java.util.Arrays;
import java.util.concurrent.*;

public class ExecutorServiceApp {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            final int w = i + 1;
            service.execute(() -> {
                System.out.println(w + " - Begin");
                try {
                    Thread.sleep(1000 + (int) (2000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(w + " - End");
            });
        }
        service.shutdown();
    }
}
