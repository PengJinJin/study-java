package root.com.java.concurrency;//: concurrency/Interrupting.java
// Interrupting a blocked thread.

import root.com.java.util.Print;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

class SleepBlocked implements Runnable {
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			Print.print("InterruptedException");
		}
		Print.print("Exiting SleepBlocked.run()");
	}
}

class IOBlocked implements Runnable {
	private InputStream in;

	public IOBlocked(InputStream is) {
		in = is;
	}

	public void run() {
		try {
			Print.print("Waiting for read():");
			in.read();
		} catch (IOException e) {
			if (Thread.currentThread().isInterrupted()) {
				Print.print("Interrupted from blocked I/O");
			} else {
				throw new RuntimeException(e);
			}
		}
		Print.print("Exiting IOBlocked.run()");
	}
}

class SynchronizedBlocked implements Runnable {
	public synchronized void f() {
		while (true) // Never releases lock
			Thread.yield();
	}

	public SynchronizedBlocked() {
		new Thread() {
			public void run() {
				f(); // Lock acquired by this thread
			}
		}.start();
	}

	public void run() {
		Print.print("Trying to call f()");
		f();
		Print.print("Exiting SynchronizedBlocked.run()");
	}
}

public class Interrupting {
	private static ExecutorService exec =
			Executors.newCachedThreadPool();

	static void test(Runnable r) throws InterruptedException {
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		Print.print("Interrupting " + r.getClass().getName());
		f.cancel(true); // Interrupts if running
		Print.print("Interrupt sent to " + r.getClass().getName());
	}

	public static void main(String[] args) throws Exception {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(3);
		Print.print("Aborting with System.exit(0)");
		System.exit(0); // ... since last 2 interrupts failed
	}
} /* Output: (95% match)
Interrupting SleepBlocked
InterruptedException
Exiting SleepBlocked.run()
Interrupt sent to SleepBlocked
Waiting for read():
Interrupting IOBlocked
Interrupt sent to IOBlocked
Trying to call f()
Interrupting SynchronizedBlocked
Interrupt sent to SynchronizedBlocked
Aborting with System.exit(0)
*///:~
