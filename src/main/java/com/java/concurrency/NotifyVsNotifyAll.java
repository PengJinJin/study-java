package com.java.concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 验证是否NotifyAll会唤醒所有的线程
 * 当notifyAll因某个特定的锁而被调用时,只有等待这个锁的任务才会被全部唤醒
 */
class Blocker {
	synchronized void waitingCall() {
		try {
			while (!Thread.interrupted()) {
				wait();
				System.out.print(Thread.currentThread() + " ");
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted!!!!!");
		}
	}

	synchronized void prod() {
		notify();
	}

	synchronized void prodAll() {
		notifyAll();
	}
}

class Task implements Runnable {
	static Blocker blocker = new Blocker();

	@Override
	public void run() {
		blocker.waitingCall();
	}
}

class Task2 implements Runnable {
	static Blocker blocker = new Blocker();

	@Override
	public void run() {
		blocker.waitingCall();
	}
}

public class NotifyVsNotifyAll {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Task());
		}
		exec.execute(new Task2());
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			boolean prod = true;

			@Override
			public void run() {
				if (prod) {
					System.out.print("\nnotify() ");
					Task.blocker.prod();
					prod = false;
				} else {
					System.out.print("\nNotifyAll() ");
					Task.blocker.prodAll();
					prod = true;
				}
			}
		}, 400, 400);// Run every .4 second
		TimeUnit.MILLISECONDS.sleep(5000);
		timer.cancel();
		System.out.println("\nTimer canceled");
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.print("Task2.blocker.prodAll() ");
		Task2.blocker.prodAll();
		TimeUnit.MILLISECONDS.sleep(500);
		System.out.println("\nshutting down");
		exec.shutdownNow();// Interrupt all tasks
	}

}


