package com.java.concurrency;

import static com.java.util.Print.print;

public class SyncObject {
	public static void main(String[] args) {
		// f和g并不会互斥,因为锁不一样
		final DualSnych snych = new DualSnych();
		new Thread(snych::f).start();
		snych.g();
	}
}

class DualSnych {
	private Object object = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			print("f");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (object) {
			for (int i = 0; i < 5; i++) {
				print("g");
				Thread.yield();
			}
		}
	}
}
