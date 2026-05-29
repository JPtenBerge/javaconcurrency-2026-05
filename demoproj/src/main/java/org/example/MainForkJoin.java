package org.example;

import java.util.concurrent.ForkJoinPool;

public class MainForkJoin {
    static void main() {
        try (var pool = new ForkJoinPool()) {
            var result = pool.invoke(new FibonacciTask(8));
            System.out.println("fibo: " + result);
        }
    }
}
