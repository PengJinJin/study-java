package root.com.java.concurrency.exercise;

import root.com.java.util.Print;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

public class E21_ThreadCooperation {

	public static void main(String args[]) throws Exception {
		Runnable coop1 = new Coop1(),
				coop2 = new Coop2(coop1);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(coop1);
		exec.execute(coop2);
		Thread.yield();
		exec.shutdown();
	}

}

class Coop1 implements Runnable {
	public Coop1() {
		Print.print("Constructed Coop1");
	}

	public void run() {
		Print.print("Coop1 going into wait");
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException ignore) {
			}
		}
		Print.print("Coop1 exited wait");
	}
}

class Coop2 implements Runnable {
	Runnable otherTask;

	public Coop2(Runnable otherTask) {
		this.otherTask = otherTask;
		Print.print("Constructed Coop2");
	}

	public void run() {
		Print.print("Coop2 pausing 5 seconds");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ignore) {
		}
		Print.print("Coop2 calling notifyAll");
		synchronized (otherTask) {
			otherTask.notifyAll();
		}
	}
}
