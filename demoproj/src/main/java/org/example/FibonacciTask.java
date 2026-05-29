package org.example;

import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Integer> {
    final int N;

    public FibonacciTask(int n) {
        this.N = n;
    }

    @Override
    protected Integer compute() {
        if (N < 1) return 1;

        var task1 = new FibonacciTask(N - 1);
        task1.fork();
        var task2 = new FibonacciTask(N - 2);
        return task2.compute() + task1.join();
    }

    // 1 1 2 3 5 8 13 21 34 55 89
}
