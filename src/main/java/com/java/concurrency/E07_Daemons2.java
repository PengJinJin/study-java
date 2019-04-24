package com.java.concurrency;


import java.util.concurrent.TimeUnit;

import static com.java.util.Print.printnb;

class Daemon2 implements Runnable {

	private Thread[] t = new Thread[10];


	@Override
	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			printnb("DaemonSpawn [" + i + "] started, ");
		}
		try {
			// To better see the effect of altering main
			// application thread's sleep time.
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < t.length; i++) {
			printnb("t[" + i + "].isDaemon() = " + t[i].isDaemon() + ", ");
		}
		while (true) {
			Thread.yield();
		}
	}
}

public class E07_Daemons2 {

	/**
	 * Our program accepts the sleep time (in milliseconds) as a command line argument.
	 * By providing different values (usually much less than 1 second),
	 * you see that the chief daemon thread (running the Daemon2 task) abruptly terminates before even reaching the endless loop.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		if (args.length != 1) {
			return;
		}
		int sleep_time = Integer.parseInt(args[0]);
		Thread d = new Thread(new Daemon2());
		d.setDaemon(true);
		d.start();
		printnb("d.isDaemon() = " + d.isDaemon() + ", ");
		TimeUnit.MILLISECONDS.sleep(sleep_time);
	}

}
