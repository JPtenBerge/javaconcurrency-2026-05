package org.example;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("hoiiii vanaf MyCallable");
        try {
            sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println("klaar met sleepen MyCallable");
        return "return van callable";
    }
}
