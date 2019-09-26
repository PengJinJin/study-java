package com.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * 求和计算
 */
public class RecursiveTaskDemo2 {

	private static class SumTask extends RecursiveTask<Integer> {

		// 设置一个阈值,分割成任务,计算求和
		private static final Integer THRESHOLD = 1000;

		private int start;
		private int end;

		public SumTask(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {
			int sum = 0;
			// 如果任务足够小就计算而不是拆分
			boolean can = (end - start) <= THRESHOLD;

			// 如果不需要分割,那么直接for循环+=计算
			if (can) {
				System.out.println("不需要分割");
				for (int i = start; i <= end; i++) {
					sum += i;
				}
			} else {
				System.out.println("需要分割");
				int mid = (end + start) / 2;
				SumTask leftTask = new SumTask(start, mid);
				// 利用ForkJoinPool中的线程异步执行第一个子任务
				leftTask.fork();

				// 创建后半段任务
				SumTask rightTask = new SumTask(mid + 1, end);

				// 同时执行第二个子任务，有可能进一步划分
				int rightResult = rightTask.compute();

				// 读取第一个子任务的结果，如果没有完成就等待
				int leftResult = leftTask.join();

				sum = leftResult + rightResult;
			}

			return sum;
		}

	}

	public static void main(String[] args) {
		long start = System.nanoTime();
		ForkJoinPool pool = new ForkJoinPool();
		SumTask task = new SumTask(1, 10000);
		/*
		Future<Integer> f = pool.submit(task);
		System.out.println(f.get());
		*/
		Integer result = pool.invoke(task);
		System.out.println(result);
		long end = System.nanoTime();
		long l = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("耗时: " + l + "ms");
		pool.shutdown();
	}

}
