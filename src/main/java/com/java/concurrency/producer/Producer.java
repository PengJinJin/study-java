package com.java.concurrency.producer;

public class Producer implements Runnable {
	Person p = null;

	public Producer(Person p) {
		this.p = p;
	}

	@Override
	public void run() {
		// 生产
		for (int i = 0; i < 50; i++) {
			if (i % 2 == 0) {
				p.push("Tom", 30);
			} else {
				p.push("Marry", 22);
			}
		}
	}
}
