package com.kkx.day04.thred;

public class ThreadDemo1 {
	public static void main(String[] args) {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("1");
					Thread.yield();
				}
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				for (int i = 6; i < 10; i++) {
					System.out.println("2");
					Thread.yield();
				}
			}
		};
		t1.start();
		t2.start();

	}
}
