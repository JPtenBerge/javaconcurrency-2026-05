package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class MainAtomics {
    static AtomicInteger counter = new AtomicInteger();

    static void main() throws InterruptedException {
        var t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementAndGet();
            }
        });
        var t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementAndGet();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("counter: " + counter.get());
    }
}
