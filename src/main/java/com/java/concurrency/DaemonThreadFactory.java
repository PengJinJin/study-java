package com.java.concurrency;

import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory {
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		// 将后台状态全部设置成了true
		t.setDaemon(true);
		return t;
	}
}
