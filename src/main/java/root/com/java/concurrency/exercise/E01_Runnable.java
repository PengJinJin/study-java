package root.com.java.concurrency.exercise;


class Printer implements Runnable {

	private static int taskCount = 0;
	private final int id = taskCount++;

	public Printer() {
		System.out.println("Printer started, ID = " + id);
	}

	@Override
	public void run() {
		System.out.println("Stage 1..., ID = " + id);
		Thread.yield();
		System.out.println("Stage 3..., ID = " + id);
		Thread.yield();
		System.out.println("Stage 4..., ID = " + id);
		Thread.yield();
		System.out.println("Printer ended, ID = " + id);
	}

}


public class E01_Runnable {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Printer()).start();
		}
	}

}
