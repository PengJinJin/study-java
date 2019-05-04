package com.java.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.java.util.Print.print;

/**
 * 添加一个BusBoy类，在上菜之后，WaitPerson通知BusBoy清理
 */
public class E26_Restaurant2 {
	public static void main(String[] args) {
		new Restaurant2();
	}
}

class Restaurant2 {
	Meal2 meal2;
	Chef2 chef2 = new Chef2(this);
	WaitPerson2 waitPerson2 = new WaitPerson2(this);
	BusBoy busBoy = new BusBoy(this);
	ExecutorService exec = Executors.newCachedThreadPool();

	Restaurant2() {
		exec.execute(chef2);
		exec.execute(waitPerson2);
		exec.execute(busBoy);
	}
}

class Meal2 {
	private final int orderNum;

	Meal2(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

class Chef2 implements Runnable {
	Restaurant2 restaurant2;
	private int count;

	public Chef2(Restaurant2 restaurant2) {
		this.restaurant2 = restaurant2;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant2.meal2 != null) {
						wait();
					}
				}
				if (++count == 10) {
					print("Out of food, closing");
					restaurant2.exec.shutdownNow();
					return;
				}
				synchronized (restaurant2.waitPerson2) {
					restaurant2.meal2 = new Meal2(count);
					restaurant2.waitPerson2.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException e) {
			print("Chef interrupted");
			e.printStackTrace();
		}
	}
}

class WaitPerson2 implements Runnable {
	Restaurant2 restaurant2;
	// 是否通知busBoy的状态
	boolean notified;

	public WaitPerson2(Restaurant2 restaurant2) {
		this.restaurant2 = restaurant2;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant2.meal2 == null) {
						wait();
					}
				}
				print("Waitperson got " + restaurant2.meal2);
				synchronized (restaurant2.busBoy) {
					restaurant2.busBoy.notified = true;
					restaurant2.busBoy.meal2 = restaurant2.meal2;
					restaurant2.busBoy.notifyAll();
				}
				synchronized (restaurant2.chef2) {
					restaurant2.meal2 = null;
					restaurant2.chef2.notify();
				}
				synchronized (this) {
					if (!notified) {
						wait();
					}
					notified = false;
				}
			}
		} catch (InterruptedException e) {
			print("WaitPerson interrupted");
			e.printStackTrace();
		}
	}
}

class BusBoy implements Runnable {
	Restaurant2 restaurant2;
	// 是否被通知的状态
	boolean notified;
	Meal2 meal2;

	public BusBoy(Restaurant2 restaurant2) {
		this.restaurant2 = restaurant2;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					if (!notified) {
						wait();// 等待是为了waitPerson上菜
					}
					notified = false;
				}
				print("Busboy cleaned up " + meal2);
				synchronized (restaurant2.waitPerson2) {
					restaurant2.waitPerson2.notified = true;
					restaurant2.waitPerson2.notifyAll();
				}
			}
		} catch (Exception e) {
			print("Busboy cleaned up " + meal2);
			e.printStackTrace();
		}
	}
}
