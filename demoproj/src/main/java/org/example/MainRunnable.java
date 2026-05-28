package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class MainRunnable {
    static void main() throws ExecutionException, InterruptedException {

        var t1 = new Thread(new MyRunnable());

        var pool = Executors.newFixedThreadPool(3);
        var future = pool.submit(new MyCallable());

        var t3 = new Thread(new MyRunnable());

        var t4 = new Thread(() -> {
            System.out.println("hoiiii vanaf runnable");
            try {
                sleep(2000);
            } catch (InterruptedException e) {}
            System.out.println("klaar met sleepen");
        });

        t1.start();
//        t2.start();
        t3.start();
        t4.start();

        System.out.println("klaar - " + future.get());

    }
}
