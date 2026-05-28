package org.example;

import static java.lang.Thread.sleep;

public class MainInterruptor {
    static void main() throws InterruptedException {


        var t = new Thread(() -> new HardeWerkerRunnable().run());
        t.start();
        sleep(3000);
//        HardeWerkerRunnable.cancel();

//        t.isInterrupted()
        System.out.println("interrupting");
         t.interrupt();
        System.out.println("interrupted");
    }
}
