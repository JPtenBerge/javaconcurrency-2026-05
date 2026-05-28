package org.example;

public class MainThreads {
    static String klaar = "";

    static void main() {
        //        var t = new Thread(() -> {
//
//            var start = currentTimeMillis();
//            for (int i = 0; i < 50_000; i++) {
//                System.out.print("x");
//            }
//            var eind = currentTimeMillis();
//            klaar = "Duration: " + (eind - start) + "ms";
//        });
//        t.setPriority(Thread.MAX_PRIORITY);
        var t = new MyThread();
        t.start();
//        try {
//            t.join(); // blocking method - sequentieel - WACHT
//        } catch (InterruptedException e) {}

        for (int i = 0; i < 50_000; i++) {
            System.out.print(".");
        }
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("klaar: " + klaar);
    }
}
