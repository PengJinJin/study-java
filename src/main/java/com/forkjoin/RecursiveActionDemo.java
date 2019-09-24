package com.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 模拟批量发送消息
 */
public class RecursiveActionDemo {

	public static class SendMgsTask extends RecursiveAction {

		private final int THRESHOLD = 10;

		private int start;
		private int end;
		private List<String> list;

		public SendMgsTask(int start, int end, List<String> list) {
			this.start = start;
			this.end = end;
			this.list = list;
		}

		@Override
		protected void compute() {
			// 如果任务足够小,不需要拆分执行
			if ((end - start) <= THRESHOLD) {
				for (int i = start; i <= end; i++) {
					System.out.println(Thread.currentThread().getName() + ": " + list.get(i));
				}
			} else {
				int mid = (start - end) / 2;
				invokeAll(new SendMgsTask(start, mid, list), new SendMgsTask(mid, end, list));
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			list.add(String.valueOf(i));
		}

		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(new SendMgsTask(0, list.size(), list));
		pool.awaitTermination(10, TimeUnit.SECONDS);
		pool.shutdown();
	}

}
