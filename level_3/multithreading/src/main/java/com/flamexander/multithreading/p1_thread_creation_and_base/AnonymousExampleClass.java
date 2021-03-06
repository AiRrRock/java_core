package com.flamexander.multithreading.p1_thread_creation_and_base;

public class AnonymousExampleClass {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        // public class AnonymousExampleClass$1 implements Runnable {
        // @Override
        //            public void run() {
        //                for (int i = 0; i < 10; i++) {
        //                    System.out.println(i);
        //                    try {
        //                        Thread.sleep(1000);
        //                    } catch (InterruptedException e) {
        //                        e.printStackTrace();
        //                    }
        //                }
        //            }
        // }
        // AnonymousExampleClass$1 r = new AnonymousExampleClass$1();
        // Thread t = new Thread(r);

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("END");
    }
}
