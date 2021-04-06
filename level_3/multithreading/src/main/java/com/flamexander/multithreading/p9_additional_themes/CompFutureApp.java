package com.flamexander.multithreading.p9_additional_themes;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class CompFutureApp {
    public static void main(String[] args) throws Exception {
        // twoStagesTask();
//        CompletableFuture<String> composeFuture
//                = CompletableFuture.supplyAsync(() -> "Hello")
//                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
//        System.out.println(composeFuture.get());
//
//
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " World"), (s1, s2) -> s1 + s2);
    }

    public static void twoStagesTask() throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenAccept(System.out::println);
        System.out.println(future.get());
    }

    public static void oneTaskExecution() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "12345");
        System.out.println(future.get());
    }

    public static void chainExecution() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(s -> "___" + s + "___");

        System.out.println(completableFuture.get());
    }

    public static void changeGenericType() {
        CompletableFuture
                .supplyAsync(() -> "123")
                .thenApply(s -> s + "11")
                .thenApply(s -> Integer.parseInt(s))
                .thenAccept(System.out::println);
    }

    public static void exceptionExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("RE");
            }
            return "Result";
        }).handle((s, t) -> s != null ? s : t.getMessage());
        System.out.println(completableFuture.get());
    }
}