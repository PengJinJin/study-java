package root.com.java.concurrency;

import root.com.java.util.Print;

import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

class ADaemon implements Runnable {
	public void run() {
		try {
			Print.print("Starting ADaemon");
			TimeUnit.SECONDS.sleep(1);
		} catch(InterruptedException e) {
			Print.print("Exiting via InterruptedException");
		} finally {
			Print.print("This should always run?");
		}
	}
}

public class DaemonsDontRunFinally {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new ADaemon());
		t.setDaemon(true);
		t.start();
		// TimeUnit.SECONDS.sleep(2);
	}
}