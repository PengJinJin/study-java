package root.com.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 使用Fork/Join，任务窃取（A线程的任务执行完了，B没执行完，A取B的来执行），来计算斐波那契数列
 */
public class RecursiveTaskDemo {
	private static class Fibonacci extends RecursiveTask<Integer> {

		final int a;

		public Fibonacci(int a) {
			this.a = a;
		}

		@Override
		protected Integer compute() {
			if (a <= 1) {
				return a;
			} else {
				Fibonacci f1 = new Fibonacci(a - 1);
				f1.fork();
				Fibonacci f2 = new Fibonacci(a - 1);
				return f2.compute() + f1.join();
			}
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		Future<Integer> f = pool.submit(new Fibonacci(5));
		System.out.println(f.get());
		pool.shutdown();
	}
}
