package root.com.java.concurrency;//: concurrency/Interrupting2.java
// Interrupting a task blocked with a ReentrantLock.

import root.com.java.util.Print;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static root.com.java.util.Print.print;

class BlockedMutex {
	private Lock lock = new ReentrantLock();

	public BlockedMutex() {
		// Acquire it right away, to demonstrate interruption
		// of a task blocked on a ReentrantLock:
		lock.lock();
	}

	public void f() {
		try {
			// This will never be available to a second task
			lock.lockInterruptibly(); // Special call
			Print.print("lock acquired in f()");
		} catch (InterruptedException e) {
			Print.print("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	BlockedMutex blocked = new BlockedMutex();

	public void run() {
		Print.print("Waiting for f() in BlockedMutex");
		blocked.f();
		Print.print("Broken out of blocked call");
	}
}

public class Interrupting2 {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
	}
} /* Output:
Waiting for f() in BlockedMutex
Issuing t.interrupt()
Interrupted from lock acquisition in f()
Broken out of blocked call
*///:~
