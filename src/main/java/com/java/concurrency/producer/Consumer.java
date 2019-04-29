package com.java.concurrency.producer;

public class Consumer implements Runnable {
	Person p;

	public Consumer(Person p) {
		this.p = p;
	}

	@Override
	public void run() {
		// 消费对象
		for (int i = 0; i < 50; i++) {
			p.pop();
		}
	}
}
