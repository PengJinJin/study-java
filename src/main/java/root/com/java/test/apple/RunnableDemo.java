package root.com.java.test.apple;

public class RunnableDemo {

	public static void main(String[] args) {
		System.out.println("before");
		Thread thread = new Thread(() -> {
			System.out.println("FAJSKLD:ASDFGUI");
		});
		thread.run();
		System.out.println("after");
	}

}
