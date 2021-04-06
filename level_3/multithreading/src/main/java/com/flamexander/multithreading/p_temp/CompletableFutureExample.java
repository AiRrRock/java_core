package com.flamexander.multithreading.p_temp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> completableFuture =
//                CompletableFuture.supplyAsync(() -> {
////                    System.out.println(Thread.currentThread().getName());
//                    return "A";
//                });
//        completableFuture.thenAccept(str -> System.out.println("Second result: " + str));
//        System.out.println(completableFuture.get());
        CompletableFuture.supplyAsync(() -> "A").thenAccept((r) -> System.out.println("result: " + r));

        CompletableFuture<String> cf1 = null, cf2 = null, cf3 = null;
        CompletableFuture.allOf(cf1, cf2, cf3).get();


    }
}
