package org.example;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class MainCompletor {
    static void main() throws InterruptedException, ExecutionException {

        var pool = Executors.newFixedThreadPool(4);
        var complet0r = new ExecutorCompletionService<Integer>(pool);

        complet0r.submit(() -> {
            sleep(1500);
            return 11;
        });
        complet0r.submit(() -> {
            sleep(3000);
            return 22;
        });
        complet0r.submit(() -> {
            sleep(800);
            return 44;
        });

//        System.out.println(complet0r.poll());
//        sleep(1000);
//        System.out.println(complet0r.poll().isDone());
//        System.out.println(complet0r.poll().get());

        System.out.println(complet0r.take().get());
        System.out.println(complet0r.take().get());
        System.out.println(complet0r.take().get());

    }
}
