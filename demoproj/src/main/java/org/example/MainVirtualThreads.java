package org.example;

import java.util.concurrent.Executors;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;

public class MainVirtualThreads {
    static void main() {
//        System.out.println("Start");
//        var start = currentTimeMillis();
//
//        try (var pool = Executors.newCachedThreadPool()) {
//
//            for (int i = 0; i < 10_000_000; i++) {
//                final var temp = i;
//
//                pool.submit(() -> {
////                    System.out.println("qSleeping #" + temp);
//                    try {
//                        sleep(120);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
////                    System.out.println("wakey wakey #" + temp);
//                });
//            }
//        }
//
//        var end = currentTimeMillis();
//
//        System.out.println("Klaar! " + (end - start) + "ms");


        System.out.println("Start zonder pool");
        var start = currentTimeMillis();

        for (int i = 0; i < 100_000_000; i++) {
            final var temp = i;

            Thread.ofVirtual().start(() -> {
//                    System.out.println("qSleeping #" + temp);
                try {
                    sleep(120); // PRIMA BLOCKING
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
//                    System.out.println("wakey wakey #" + temp);
            });
        }

        var end = currentTimeMillis();

        System.out.println("Klaar! " + (end - start) + "ms");


    }
}
