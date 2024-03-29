package root.com.java.concurrency.exercise;

import root.com.java.util.Generator;

import java.util.Arrays;

class Fibonacci implements Generator<Integer>, Runnable {

	private int count;
	private final int n;

	public Fibonacci(int n) {
		this.n = n;
	}

	private int fib(int n) {
		if (n < 2) return 1;
		return fib(n - 2) + fib(n - 1);
	}

	@Override
	public Integer next() {
		return fib(count++);
	}

	@Override
	public void run() {
		Integer[] sequence = new Integer[n];
		for (int i = 0; i < n; i++) {
			sequence[i] = next();
		}
		System.out.println("Seq. of " + n + ": " + Arrays.toString(sequence));
	}
}

public class E02_Fibonacci {

	public static void main(String[] args) {
		for (int i = 1; i <= 6; i++) {
			new Thread(new Fibonacci(i)).start();
		}
	}

}
