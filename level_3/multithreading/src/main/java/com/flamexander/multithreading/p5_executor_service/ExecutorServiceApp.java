package com.flamexander.multithreading.p5_executor_service;

import java.util.concurrent.*;
import java.util.stream.Stream;

public class ExecutorServiceApp {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName());
        ExecutorService service = Executors.newFixedThreadPool(4);

        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "Java";
            }
        });
        System.out.println(future.get());


//        service.shutdown();


//        Thread t1 = new Thread(() -> {
//            System.out.println(1);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (10 < 20) {
//                throw new RuntimeException();
//            }
//            System.out.println(2);
//        });
//        t1.start();
//        t1.join();
//        while(true) {
//
//        }


//        ExecutorService exec = Executors.newFixedThreadPool(4, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable runnable) {
//                Thread t = new Thread(runnable);
//                t.setPriority(1);
//                t.setDaemon(false);
//                t.setName("ABC");
//                return t;
//            }
//        });
//        exec.execute(() -> {
//            while (true) {
//            }
//        });
//        exec.shutdown();

//        Future<String> future = exec.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                Thread.sleep(1000);
//                if (10 < 20) {
//                    throw new RuntimeException("111");
//                }
//                return "java";
//            }
//        });

//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        exec.shutdown();


//        ExecutorService serv = Executors.newFixedThreadPool(5);
//        for (int i = 0; i <  12;i++) {
//            String w = "#" + (i + 1);
//            serv.execute(() -> {
//                System.out.println(Thread.currentThread().getName() + " - " + w + "-start");
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + " - " + w + "-end");
//            });
//        }
//        serv.shutdown();

//        ExecutorService service = Executors.newFixedThreadPool(4);
//        Future<String> stringFuture = service.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                Thread.sleep(2000);
////                int x = 10 / 0;
//                return "Java";
//            }
//        });
//
//        try {
//            String result = stringFuture.get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        service.shutdown();
    }
}
