package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class MainExecutor {
    static void main() {
        try (var executor = Executors.newFixedThreadPool(20)) {
            for (int i = 0; i < 10_000; i++) {
                final var temp = i;

                executor.submit(() -> {
                    System.out.printf("Hier een worker %d\n", temp);

                    try {
                        sleep(1000 + (temp * 100));
                    } catch (InterruptedException e) {
                    }
                    System.out.printf("Klaar! %d\n", temp);
                });
            }
        }
    }
}
