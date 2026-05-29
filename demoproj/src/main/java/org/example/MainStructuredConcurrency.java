package org.example;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;

import static java.lang.Thread.sleep;

public class MainStructuredConcurrency {
    static void main() throws InterruptedException {

        // Promise.all()
        // Promise.allSettled()
        // Promise.any()

        try (var scope = StructuredTaskScope.open(Joiner.<String>allSuccessfulOrThrow())) {
            scope.fork(() -> {
                System.out.println("begin eerste");
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    System.out.println("catch eerste");
                    throw new RuntimeException(e);
                }
                System.out.println("return eerste");
                return "Acht";
            });
            scope.fork(() -> {
                throw new RuntimeException("ohohhhh");
//                try {
//                    sleep(2500);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                return "Zes";
            });
            scope.fork(() -> {
                System.out.println("begin laatste");
                try {
                    sleep(800);
                } catch (InterruptedException e) {
                    System.out.println("catch laatste");
                    throw new RuntimeException(e);
                }
                System.out.println("return laatste");
                return "Vliegtuig";
            });

            var results = scope.join();
            System.out.println("results: " + results);

        } catch (RuntimeException e) {
            System.out.println("oei dat ging mis: " + e.getMessage());
        }

    }
}
