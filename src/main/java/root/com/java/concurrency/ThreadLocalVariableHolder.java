package root.com.java.concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 每个不同的线程创建不同的变量,各个线程的变量互不干扰
public class ThreadLocalVariableHolder {
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
		private Random random = new Random(47);

		@Override
		protected synchronized Integer initialValue() {
			return random.nextInt(10);
		}
	};

	public static void increment() {
		value.set(value.get() + 1);
	}

	public static int get() {
		return value.get();
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			exec.execute(new Accessor(i));
		}
		TimeUnit.SECONDS.sleep(3);
		exec.shutdown();
	}

} /* Output: (sample)
#0: 4456
#0: 4457
#0: 4458
#2: 3433
#2: 3434
#2: 3435
#2: 3436
#2: 3437
#2: 3438
#2: 3439
#1: 3482
#1: 3483
#1: 3484
#1: 3485
...
*///:~

class Accessor implements Runnable {

	private final int id;

	public Accessor(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ThreadLocalVariableHolder.increment();
			System.out.println(this);
			Thread.yield();
		}
	}

	@Override
	public String toString() {
		return "#" + id + ": " + ThreadLocalVariableHolder.get();
	}
}
