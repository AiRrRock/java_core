package com.flamexander.multithreading.p_temp;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TempApp2 {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<String> tls = new ThreadLocal<>();
        tls.set("1");
        System.out.println(tls.get());
        new Thread(() -> {
            tls.set("2");
            System.out.println(tls.get());
        }).start();
        Thread.sleep(500);
        System.out.println(tls.get());
        new Thread(() -> {
            System.out.println(tls.get());
        }).start();


//        FutureTask<String> ft = new FutureTask<>(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println(Thread.currentThread().getName());
//                return "A";
//            }
//        });
//        new Thread(ft).start();
//        try {
//            System.out.println(ft.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        service.shutdown();
//
//        try {
//            System.out.println("main> " + f.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

    }
}
