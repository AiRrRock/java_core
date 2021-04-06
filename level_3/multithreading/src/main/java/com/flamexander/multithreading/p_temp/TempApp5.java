package com.flamexander.multithreading.p_temp;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TempApp5 {
    public static void main(String[] args) throws Exception {
        // 1. Если завершилась работа потока, в котором был порожден поток-демон,
        // будет ли поток-демон остановлен в этом случае? При условии
        // что еще работают другие обычные потоки
        // 2. Можно ли перезапустить завершившийся поток?
        // 3. У потока есть state стоит ли на него ориентироваться?
        // 4. Что дает приоритет?
        // 5. В каком случае возникает race condition?
        // 6. Что такое InterruptedException?
        // 7. Что может выступать в роли монитора?
        // Что возможно стоит использовать в роли монитора?
        // 8. В чем разница между synchronized block и lock?
        // 9. Решает ли volatile проблему race condition?
        // 10. Можно ли ограничить кол-во потоков в CachedThreadPool?
        // 11. В каких ситуациях применяется SingleThreadExecutor
        // 12. Можно ли в пуле потоков настроить параметры потоков?
        // 13. Назовите особенности: Semaphore, CyclicBarrier, CountdownLatch,
        // ConcurrentHashMap, Hashtable, Vector, Lock (RL, RRWL),
        // Collections.synchronized(map, set, list), ...
        // 14. В чем разница между Runnable и Callable?
        // 15. Что такое тип ...Future?
        // 16.

//        ExecutorService es = Executors.newFixedThreadPool(4, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable runnable) {
//                Thread t = new Thread(runnable);
//                t.setPriority(10);
//                t.setDaemon(true);
//                return t;
//            }
//        });

//        Thread t = new Thread(() -> {
//            while (true) {
//                if(Thread.currentThread().isInterrupted()) {
//                    break;
//                }
//                System.out.println(1);
//            }
//        });
//        t.start();

//        ExecutorService es = Executors.newFixedThreadPool(4);
//        es.invokeAll(Arrays.asList(
//                () -> {
//                    Thread.sleep(1000);
//                    System.out.println("1s");
//                    return true;
//                }     ,
//                () -> {
//                    Thread.sleep(2000);
//                    System.out.println("2s");
//                    return true;
//                }
//        ));
//        System.out.println("end");
//
//        es.shutdown();

//        Thread.sleep(800);
//        t.interrupt();

        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(5000);
                return "Java";
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
