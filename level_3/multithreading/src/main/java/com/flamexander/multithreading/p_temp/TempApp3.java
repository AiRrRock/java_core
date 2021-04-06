package com.flamexander.multithreading.p_temp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class TempApp3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setPriority(10);
            return t;
        });

        Future<String> res = executorService.submit(() -> "A");
        try {
            res.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        FutureTask<String> fts = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Z";
            }
        });
        new Thread(fts).start();

        Map<String, String> map = new HashMap<>();
        // заполняем map
        Map sm = Collections.synchronizedMap(map);
        Map sm2 = new ConcurrentHashMap(map);

    }
}
