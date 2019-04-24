package com.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepingTask extends LiftOff {

	@Override
	public void run() {
		try {
			while (countDown-- > 0) {
				System.out.print(status());
				// old-style
				// Thread.sleep(200);
				// new-style
				TimeUnit.MILLISECONDS.sleep(200);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}

}
