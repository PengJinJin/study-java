package root.com.java.concurrency.exercise;

import java.util.concurrent.ThreadFactory;

class ExceptionThread2 implements Runnable {

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("run() by " + t);
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("caught " + e);
	}
}

class HandlerFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + " create new Thread");
		Thread t = new Thread();
		System.out.println("created " + t);
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		return t;
	}
}

public class CaptureUncaughtException {
	public static void main(String[] args) {
		try {
			double d = 2 / 0;
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.toString());
		}
//		ExecutorService exec = Executors.newCachedThreadPool(new HandlerFactory());
//		exec.execute(new ExceptionThread2());
	}
}


