package root.com.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 破坏线程死锁只需要破坏4个条件的其中一个，一般破坏第四个
 * 这里是，最后一个Philosopher先拿起和放下左边的Chopstick，剩下的Philosopher不管
 */
public class FixedDiningPhilosophers {
	public static void main(String[] args) throws Exception {
		int ponder = 5;
		if (args.length > 0) {
			ponder = Integer.parseInt(args[0]);
		}
		int size = 5;
		if (args.length > 1) {
			size = Integer.parseInt(args[1]);
		}
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; i++) {
			sticks[i] = new Chopstick();
		}
		// 哲学家人数与筷子数一致，每个哲学家吃饭，需要拿起左右两边的筷子
		// 如果是最后一个，他先拿起和放下左边的筷子，其他人先拿起右边的
		for (int i = 0; i < size; i++) {
			if (i < size - 1) {
				exec.execute(new Philosopher(sticks[i], sticks[i + 1], i, ponder));
			} else {
				exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
			}
		}
		if (args.length == 3 && args[2].equals("timeout")) {
			TimeUnit.SECONDS.sleep(5);
		} else {
			System.out.println("Press 'Enter' to quit.");
			System.in.read();
		}
		exec.shutdownNow();
	}
}
