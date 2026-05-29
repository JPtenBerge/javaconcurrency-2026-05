package org.example;

import java.util.concurrent.StructuredTaskScope;

import static java.lang.Thread.sleep;

public class MainScopedValue {
    static final ScopedValue<String> USER = ScopedValue.newInstance();

    static void main() throws InterruptedException {

        // threadlocal, maar zonder gezeik van de threadlocal

        ScopedValue.where(USER, "Victor").run(() -> {
            try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<String>allSuccessfulOrThrow())) {
                scope.fork(() -> {
                    try {
                        sleep(1500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Gebruiker hier 1: " + USER.get();
                });
                scope.fork(() -> {
                    try {
                        sleep(800);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Gebruiker hier 2: " + USER.get();
                });
                scope.fork(() -> {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "Gebruiker hier 3: " + USER.get();
                });

                var results = scope.join();
                System.out.println("results: " + results);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        ScopedValue.where(USER, "JP").run(() -> {
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Gebruiker hier 1: " + USER.get());
        });

        ScopedValue.where(USER, "Dennis").run(() -> {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Gebruiker hier 2: " + USER.get());
        });

        sleep(3000);
        System.out.println("klaar");
        System.out.println("klaar 2 " + USER.get());
    }
}
