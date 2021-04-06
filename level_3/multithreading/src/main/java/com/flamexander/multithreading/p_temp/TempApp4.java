package com.flamexander.multithreading.p_temp;

import java.util.concurrent.*;

public class TempApp4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                if (10 < 100) {
                    throw new ArithmeticException();
                }
                return "A";
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
