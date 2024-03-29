package root.com.java.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * �쳣�����ᱻcatch
 */
public class ExceptionThread implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		try {
			ExecutorService service = Executors.newCachedThreadPool();
			service.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
