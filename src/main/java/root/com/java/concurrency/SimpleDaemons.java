package root.com.java.concurrency;

import root.com.java.util.Print;

import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

public class SimpleDaemons implements Runnable {
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				Print.print(Thread.currentThread() + " " + this);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		Print.print("All daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
