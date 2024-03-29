package root.com.java.concurrency;

import root.com.java.util.Print;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

public class PipedIO {

	public static void main(String[] args) throws Exception {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}

}

class Sender implements Runnable {
	private Random rand = new Random(47);
	private PipedWriter out = new PipedWriter();

	public PipedWriter getOut() {
		return out;
	}

	@Override
	public void run() {
		try {
			while (true) {
				for (char c = 'A'; c <= 'z'; c++) {
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
			}
		} catch (IOException e) {
			Print.print(e + ": Sender write exception");
		} catch (InterruptedException e) {
			Print.print(e + ": Sender sleep InterruptedException");
		}
	}
}

class Receiver implements Runnable {
	private PipedReader in;

	public Receiver(Sender sender) throws IOException {
		this.in = new PipedReader(sender.getOut());
	}

	@Override
	public void run() {
		try {
			while (true) {
				// 阻止字符出现
				Print.printnb("Read: " + (char) in.read() + ", ");
			}
		} catch (IOException e) {
			Print.print(e + ": Receiver read IOException");
		}
	}
}
