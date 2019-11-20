package com.java.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest2 {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(4);

		for (int i = 0; i < 4; i++) {
			Runnable runnable = () -> {
				try {
					System.out.println("Player " + Thread.currentThread().getName() + " waiting for order");
					cdOrder.await();
//					System.out.println(3333);
					System.out.println("Player " + Thread.currentThread().getName() + " receive order");
					Thread.sleep((long) (Math.random() * 3000));
					System.out.println("Player " + Thread.currentThread().getName() + " reach destination");
					cdAnswer.countDown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			service.execute(runnable);
		}

		Thread.sleep((long) (Math.random() * 3000));

		System.out.println("referee " + Thread.currentThread().getName() + " Coming soon password");
		cdOrder.countDown();
//		System.out.println(1111);
		System.out.println("referee " + Thread.currentThread().getName() + "已发送口令，正在等待所有选手到达终点");
		cdAnswer.await();
		System.out.println("all player reach destination");
		System.out.println("referee " + Thread.currentThread().getName() + " sort");
		service.shutdown();
	}
}
