package com.java.concurrency;

/**
 * 筷子🥢类
 */
public class Chopstick {
	public boolean taken = false;

	/**
	 * 当筷子被使用，调用此方法
	 *
	 * @throws InterruptedException
	 */
	public synchronized void take() throws InterruptedException {
		while (taken) {
			wait();
		}
		taken = true;
	}

	/**
	 * 使用完后调用该方法，表示已经使用完了
	 */
	public synchronized void drop() {
		taken = false;
		notifyAll();
	}
}
