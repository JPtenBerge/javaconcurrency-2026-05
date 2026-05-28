package org.example;

import static java.lang.System.currentTimeMillis;

public class MyThread extends Thread {
    @Override
    public void run() {
        var start = currentTimeMillis();
        for (int i = 0; i < 50_000; i++) {
            System.out.print("o");
        }
        var eind = currentTimeMillis();
    }
}
