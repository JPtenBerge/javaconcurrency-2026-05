package org.example;

public class MainThreadLocal {

    static ThreadLocal<Integer> getalletje = new ThreadLocal<>();

    static void main() throws InterruptedException {
        getalletje.set(4);

        var t1 = new Thread(() -> {
            System.out.println("eerste thread " + getalletje.get());
            getalletje.set(42);
        });
        var t2 = new Thread(() -> {
            System.out.println("tweede thread" + getalletje.get());
            getalletje.set(8);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Getalletje: " + getalletje.get());



    }
}
