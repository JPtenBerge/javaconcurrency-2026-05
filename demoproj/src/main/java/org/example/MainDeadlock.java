package org.example;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainDeadlock {

    static Object lockObj1 = new Object();
    static Object lockObj2 = new Object();

    static List<Integer> getallen = new ArrayList<>();

    static void main() throws InterruptedException {
        var t1 = new Thread(() -> {
            System.out.println("t1 starten");
            synchronized (lockObj1) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lockObj2) {
                    getallen.add(14);
                }
            }
        });
        var t2 = new Thread(() -> {
            System.out.println("t2 starten");
            synchronized (lockObj2) {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lockObj1) {
                    getallen.add(28);
                }
            }
        });

        System.out.println("starten");
        t1.start();
        sleep(100);
        t2.start();

        System.out.println("joinen");
        t1.join();
        t2.join();
        System.out.println("en klaar!");
    }
}
