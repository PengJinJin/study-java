package com.kkx.day04.thred;

import java.util.concurrent.TimeUnit;

public class ThreadDemo2 {
	public static void main(String[] args) {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("继续睡");
					try {
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						System.out.println("破相了");
					}
				}
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 8; i++) {
					System.out.println("砸墙");
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						;
					}

				}
				t1.interrupt();
			}
		};
		t1.start();
		t2.start();
	}
}
