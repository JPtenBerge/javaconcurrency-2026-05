package org.example;

import static java.lang.Thread.sleep;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("hoiiii vanaf runnable");
        try {
            sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println("klaar met sleepen");
    }
}
