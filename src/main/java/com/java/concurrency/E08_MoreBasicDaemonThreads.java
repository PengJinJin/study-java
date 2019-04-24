package com.java.concurrency;

import java.util.concurrent.TimeUnit;

public class E08_MoreBasicDaemonThreads {

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new LiftOff());
			t.setDaemon(true);
			t.start();
		}
		System.out.println("Waiting for LiftOff");
	}

}
