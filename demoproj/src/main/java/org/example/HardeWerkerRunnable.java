package org.example;

import static java.lang.Thread.sleep;

public class HardeWerkerRunnable {

//    private static volatile boolean canceled = false;

    public void run() {
        while (true) {
            System.out.println("working hard");

            try {
                sleep(10000);
                System.out.println("helemaal klaar met sleep" + Thread.currentThread().isInterrupted());
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

//    public static void cancel() {
//        canceled = true;
//    }
