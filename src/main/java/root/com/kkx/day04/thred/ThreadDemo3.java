package root.com.kkx.day04.thred;

import java.util.concurrent.TimeUnit;

public class ThreadDemo3 {
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println(i);
					try {
						TimeUnit.MILLISECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.setDaemon(true);
		t.start();

		TimeUnit.MILLISECONDS.sleep(400);
		System.out.println("ÄDddasa");
	}
}
