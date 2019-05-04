package com.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.java.util.Print.print;
import static com.java.util.Print.printnb;

public class WaxOMatic2 {

	public static void main(String[] args) throws InterruptedException {
		Car2 car2 = new Car2();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff2(car2));
		exec.execute(new WaxOn2(car2));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}

}

/**
 * 汽车对象
 */
class Car2 {
	// 锁
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	// 打蜡状态
	private boolean waxOn = false;

	/**
	 * 打蜡
	 */
	public void waxed() {
		lock.lock();
		try {
			waxOn = true;// 打蜡完成，准备抛光
			// =notifyAll
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 抛光
	 */
	public void buffed() {
		lock.lock();
		try {
			waxOn = false;// 抛光完成，准备打蜡
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 等待打蜡完成
	 */
	public void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			while (!waxOn) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 等待抛光完成
	 */
	public void waitForBuffing() throws InterruptedException {
		lock.lock();
		try {
			while (waxOn) {
				condition.await();
			}
		} finally {
			lock.unlock();
		}
	}
}

/**
 * 打蜡类
 */
class WaxOn2 implements Runnable {
	private Car2 car2;

	public WaxOn2(Car2 car2) {
		this.car2 = car2;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				printnb("Wax On! ");
				TimeUnit.MILLISECONDS.sleep(200);
				car2.waxed();
				car2.waitForBuffing();
			}
		} catch (InterruptedException e) {
			print("Exiting via interrupt");
		}
		print("Ending Wax On task");
	}
}

/**
 * 打蜡完成，抛光
 */
class WaxOff2 implements Runnable {
	Car2 car2;

	public WaxOff2(Car2 car2) {
		this.car2 = car2;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				car2.waitForWaxing();
				printnb("Wax Off!");
				TimeUnit.MILLISECONDS.sleep(200);
				car2.buffed();
			}
		} catch (InterruptedException e) {
			print("Exiting via interrupt");
		}
		print("Ending Wax Off task");
	}
}
