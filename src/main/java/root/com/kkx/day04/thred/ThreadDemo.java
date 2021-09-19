package root.com.kkx.day04.thred;

public class ThreadDemo {
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
			}
		}.start();
		new Thread(new Runnable() {
			@Override
			public void run() {

			}
		}).start();

	}
}
