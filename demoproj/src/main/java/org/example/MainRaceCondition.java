package org.example;

import java.util.HashMap;

public class MainRaceCondition {
    static int counter = 0;
    static final HashMap<Integer, Integer> mappie = new HashMap<>();
    private static final Object lockObj = new Object();

    void main() throws InterruptedException {
        var t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                synchronized (lockObj) { // fine-grained locking - jij weet niet wie jouw instantie waarvoor gebruiken
                    counter++;
                }
            }
        });
        var t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                synchronized (lockObj) {
                    counter++; // ++ is not atomic
                }

//                var temp = counter;
//                temp = temp + 1;
//                counter = temp;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("counter: " + counter);

//        var t1 = new Thread(() -> {
//            for (int i = 0; i < 100_000; i++) {
//                synchronized (mappie) {
//                    System.out.print("x");
//                    mappie.put(i, i);
//                }
//            }
//        });
//        var t2 = new Thread(() -> {
//            for (int i = 100_000; i < 200_000; i++) {
//                synchronized (mappie) {
//                    System.out.print(".");
//                    mappie.put(i, i);
//                }
//            }
//        });
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println("size: " + mappie.size());
    }
}
