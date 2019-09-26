package com.futuretask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTaskForMultiCompute {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTaskForMultiCompute compute = new FutureTaskForMultiCompute();
		// 创建任务集合
		List<FutureTask<Integer>> taskList = new ArrayList<>();
		// 创建线程池
		ExecutorService exec = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 10; i++) {
			// 传入Callable对象创建FutureTask对象
			FutureTask<Integer> ft = new FutureTask<>(compute.new ComputeTask(i, String.valueOf(i)));
			taskList.add(ft);
			// 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)一次性提交所有任务
			exec.submit(ft);
		}

		System.out.println("所有计算任务提交完毕, 主线程接着干其他事情！");

		Integer total = 0;

		for (FutureTask<Integer> task : taskList) {
			total += task.get();
		}

		exec.shutdown();
		System.out.println("结果是:" + total);
	}

	private class ComputeTask implements Callable<Integer> {
		private int result;
		private String taskName;

		public ComputeTask(int result, String taskName) {
			this.result = result;
			this.taskName = taskName;
			System.out.println("生成子线程计算任务: " + taskName);
		}

		public String getTaskName() {
			return taskName;
		}

		@Override
		public Integer call() throws Exception {
			result += (101 * 50);
			for (int i = 1; i <= 100; i++) {
				result += i;
			}
			TimeUnit.MILLISECONDS.sleep(3000);
			System.out.println("子线程[" + taskName + "]计算任务完成");
			return result;
		}
	}
}
