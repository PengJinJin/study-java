package root.com.java.concurrency.exercise;

import root.com.java.util.Print;

import static root.com.java.util.Print.print;

public class E15_SyncObject {
	public static void main(String[] args) {
		// 如果是同一个监视器锁的话,会互斥,等待f执行完再开始g再开始h
		Print.print("SingleSynch text");
		final SingleSynch singleSync = new SingleSynch();
		new Thread(singleSync::f).start();
		new Thread(singleSync::g).start();
		new Thread(singleSync::h).start();

		// 不同的监视器锁并不会互斥
		final TripleSynch tripleSynch = new TripleSynch();
		new Thread(tripleSynch::f).start();
		new Thread(tripleSynch::g).start();
		new Thread(tripleSynch::h).start();

	}
}

class TripleSynch {
	private Object o1 = new Object();
	private Object o2 = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			Print.print("f");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (o1) {
			for (int i = 0; i < 5; i++) {
				Print.print("g");
				Thread.yield();
			}
		}
	}

	public void h() {
		synchronized (o2) {
			for (int i = 0; i < 5; i++) {
				Print.print("h");
				Thread.yield();
			}
		}
	}
}

class SingleSynch {
	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			Print.print("f");
			Thread.yield();
		}
	}

	public synchronized void g() {
		for (int i = 0; i < 5; i++) {
			Print.print("g");
			Thread.yield();
		}
	}

	public synchronized void h() {
		for (int i = 0; i < 5; i++) {
			Print.print("h");
			Thread.yield();
		}
	}
}
