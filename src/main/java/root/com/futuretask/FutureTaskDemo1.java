package root.com.futuretask;

import java.util.concurrent.*;

/**
 * FutureTask在高并发环境下确保任务只执行一次
 */
public class FutureTaskDemo1 {

	static class Task implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			System.out.println("子线程正在计算");
			TimeUnit.MILLISECONDS.sleep(3000);
			int sum = 0;
			for (int i = 1; i < 100; i++) {
				sum += i;
			}
			return sum;
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		Task task = new Task();
		FutureTask<Integer> futureTask = new FutureTask<>(task);
		exec.submit(futureTask);
		exec.shutdown();

		TimeUnit.MILLISECONDS.sleep(1000);

		System.out.println("主线程继续执行");
		Integer integer = futureTask.get();
		System.out.println("执行结果: " + integer);

		System.out.println("全部执行完毕");

	}

}
