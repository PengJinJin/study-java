package com.java.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * service.shutdown();用于有序关闭过程
 * JDK声明:关闭后,执行程序最终将终止，此时没有任务正在执行，没有任务正在等待执行，也没有任何新任务可以提交
 */
public class E03_Runnable2 {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			service.execute(new Printer());
		}
		Thread.yield();
		service.shutdown();

		service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			service.execute(new Printer());
		}
		Thread.yield();
		service.shutdown();

		service = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			service.execute(new Printer());
		}
		Thread.yield();
		service.shutdown();
	}

}
