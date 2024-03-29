package root.com.java.concurrency;


import root.com.java.util.Print;

import java.util.concurrent.TimeUnit;

class Daemon implements Runnable {

	private Thread[] t = new Thread[10];

	@Override
	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			Print.printnb("DaemonSpawn " + i + " started, ");
		}
		for (int i = 0; i < t.length; i++) {
			Print.printnb("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
		}
		while (true) {
			Thread.yield();
		}
	}
}

class DaemonSpawn implements Runnable {

	@Override
	public void run() {
		while (true) {
			Thread.yield();
		}
	}
}

public class Daemons {

	public static void main(String[] args) throws InterruptedException {
		Thread d = new Thread(new Daemon());
		// 如果它是一个后台线程, 那么它创建的任何子类也自动设置为后台线程
		d.setDaemon(true);
		d.start();
		Print.printnb("d.isDaemon() = " + d.isDaemon() + ", ");
		TimeUnit.SECONDS.sleep(1);
	}
}
