package root.com.java.concurrency;

import root.com.java.util.Print;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

/**
 * 哲学家类
 */
public class Philosopher implements Runnable {
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);

	public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponderFactor;
	}

	private void pause() throws InterruptedException {
		if (ponderFactor == 0) return;
		// 如果ponderFactor因子不为0，便会思考
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 思考
				Print.print(this + " thinking");
				pause();
				// 拿右边筷子
				Print.print(this + " grabbing right");
				right.take();
				// 拿左边筷子
				Print.print(this + " grabbing left");
				left.take();
				// 开吃
				Print.print(this + " eating");
				pause();
				// 吃完释放
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			Print.print(this + " exiting via interrupt");
		}
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}
