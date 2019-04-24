package com.java.concurrency.exercise;


import com.java.util.Generator;

import java.util.ArrayList;
import java.util.concurrent.*;

class FibonacciSum implements Generator<Integer>, Callable<Integer> {

	private int count;
	private final int n;

	public FibonacciSum(int n) {
		this.n = n;
	}

	@Override
	public Integer next() {
		return fib(count++);
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += next();
		}
		return sum;
	}

	public int fib(int n) {
		if(n < 2) return 1;
		return fib(n - 2) + fib(n - 1);
	}

}

public class E05_FibonacciSum {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> futures = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			futures.add(exec.submit(new FibonacciSum(i)));
		}
		Thread.yield();
		exec.shutdown();
		for (Future<Integer> fi : futures) {
			try {
				System.out.println(fi.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
