package root.com.java.concurrency.exercise;

import root.com.java.util.Print;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static root.com.java.util.Print.print;

public class E16_ExplicitSyncObject {
	public static void main(String[] args) throws Exception {
		final ExplicitSingleSynch singleSync =
				new ExplicitSingleSynch();
		final ExplicitTripleSynch tripleSync =
				new ExplicitTripleSynch();
		Print.print("Test ExplicitSingleSynch...");
		Thread t1 = new Thread() {
			public void run() {
				singleSync.f();
			}
		};
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				singleSync.g();
			}
		};
		t2.start();
		singleSync.h();
		t1.join(); // Wait for t1 to finish its work
		t2.join(); // Wait for t2 to finish its work
		Print.print("Test ExplicitTripleSynch...");
		new Thread() {
			public void run() {
				tripleSync.f();
			}
		}.start();
		new Thread() {
			public void run() {
				tripleSync.g();
			}
		}.start();
		tripleSync.h();
	}
}

class ExplicitSingleSynch {
	private Lock lock = new ReentrantLock();

	public void f() {
		lock.lock();
		try {
			for (int i = 0; i < 5; i++) {
				Print.print("f()");
				Thread.yield();
			}
		} finally {
			lock.unlock();
		}
	}

	public void g() {
		lock.lock();
		try {
			for (int i = 0; i < 5; i++) {
				Print.print("g()");
				Thread.yield();
			}
		} finally {
			lock.unlock();
		}
	}

	public void h() {
		lock.lock();
		try {
			for (int i = 0; i < 5; i++) {
				Print.print("h()");
				Thread.yield();
			}
		} finally {
			lock.unlock();
		}
	}
}

class ExplicitTripleSynch {
	private Lock lockF = new ReentrantLock();
	private Lock lockG = new ReentrantLock();
	private Lock lockH = new ReentrantLock();

	public void f() {
		lockF.lock();
		try {
			for (int i = 0; i < 5; i++) {
				Print.print("f()");
				Thread.yield();
			}
		} finally {
			lockF.unlock();
		}
	}

	public void g() {
		lockG.lock();
		try {
			for (int i = 0; i < 5; i++) {
				Print.print("g()");
				Thread.yield();
			}
		} finally {
			lockG.unlock();
		}
	}

	public void h() {
		lockH.lock();
		try {
			for (int i = 0; i < 5; i++) {
				Print.print("h()");
				Thread.yield();
			}
		} finally {
			lockH.unlock();
		}
	}
}