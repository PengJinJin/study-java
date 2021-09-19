package root.com.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程死锁例子<br/>
 * 线程死锁的条件，必须全部满足以下4点：<br/>
 * 1，互斥条件，任务中至少有一个资源是不能共享的<br/>
 * 2，至少有一个任务，它必须占有一个资源，并且正在等待其他任务持有的资源<br/>
 * 3，任务不能被抢占，任务必须把资源释放当作普通事件，都很有礼貌<br/>
 * 4，循环等待，1等2，2等3，。。。。n等1<br/>
 */
public class DeadlockingDiningPhilosopher {
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
		for (int i = 0; i < size; i++) {
			exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, ponder));
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
