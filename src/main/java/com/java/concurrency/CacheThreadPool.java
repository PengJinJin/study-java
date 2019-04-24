package com.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Executors管理Thread对象
 */
public class CacheThreadPool {

	public static void main(String[] args) {
		// newCachedThreadPool为每个任务都创建一个线程
		// FixedThreadPool创建有限的线程集执行任务
		// SingleThreadPool很像线程数量为1的FixedThreadPool,适用于长期存活的任务,它是按照顺序执行的,如果多个任务将会排队
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

}
