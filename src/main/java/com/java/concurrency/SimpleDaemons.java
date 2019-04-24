package com.java.concurrency;

import java.util.concurrent.TimeUnit;

import static com.java.util.Print.print;

public class SimpleDaemons implements Runnable {
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				print(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		print("All daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
