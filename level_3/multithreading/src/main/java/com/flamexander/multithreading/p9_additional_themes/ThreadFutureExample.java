package com.flamexander.multithreading.p9_additional_themes;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                System.out.println(1);
                return "Java";
            }
        });
        new Thread(task).start();
        System.out.println(task.get());
    }
}
