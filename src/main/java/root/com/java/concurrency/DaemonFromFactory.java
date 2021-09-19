package root.com.java.concurrency;

import root.com.java.util.Print;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

public class DaemonFromFactory implements Runnable {
	@Override
	public void run() {
		try {
			while (true) {
				TimeUnit.MILLISECONDS.sleep(100);
				Print.print(Thread.currentThread() + " " + this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// ExecutorService接收一个ThreadFactory对象,用来被创建新的线程
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			exec.execute(new DaemonFromFactory());
		}
		Print.print("All daemons started");
		TimeUnit.MILLISECONDS.sleep(500);
	}

}
