package root.com.java.concurrency;

public class SimpleThread extends Thread {

	private int countDown = 5;
	private static int threadCount = 0;

	public SimpleThread() {
		// 赋值线程的name
		super(Integer.toString(++threadCount));
		start();
	}

	@Override
	public String toString() {
		return "#" + getName() + "(" + countDown + "), ";
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new SimpleThread();
		}
	}

}
