package root.com.java.concurrency.exercise;

import root.com.java.util.Print;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

/******************** Exercise 31 ************************
 * Change DeadlockingDiningPhilosophers.java so that when a
 * philosopher is done with their chopsticks, they drop them
 * into a bin. When a philosopher wants to eat, they take
 * the next two available chopsticks from the bin. Does this
 * eliminate the possibility of deadlock? Can you
 * reintroduce deadlock by simply reducing the number of
 * available chopsticks?
 *********************************************************/
public class E31_DiningPhilosophers2 {
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.err.println("usage:\n" +
							"java E31_DiningPhilosophers2 " +
							"numberOfPhilosophers ponderFactor deadlock " +
							"timeout\n" + "A nonzero ponderFactor will " +
							"generate a random sleep time during think().\n" +
							"If deadlock is not the string " +
							"'deadlock', the program will not deadlock.\n" +
							"A nonzero timeout will stop the program after " +
							"that number of seconds.");
			System.exit(1);
		}
		ChopstickBin bin = new ChopstickBin();
		int size = Integer.parseInt(args[0]);
		int ponder = Integer.parseInt(args[1]);
		for (int i = 0; i < size; i++) {
			bin.put(new Chopstick(i));
		}
		// One additional chopstick guarantees that at least
		// one philosopher can eat without blocking.
		if (!args[2].equals("deadlock")) {
			// 如果相同的筷子和哲学家会快速陷入死锁，但加入一根额外的筷子可以防止死锁
			bin.put(new Chopstick(size));
		}
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < size; i++)
			exec.execute(new Philosopher(i, ponder, bin));
		if (args.length == 4)
			TimeUnit.SECONDS.sleep(Integer.parseInt(args[3]));
		else {
			Print.print("Press 'ENTER' to quit");
			System.in.read();
		}
		exec.shutdownNow();
	}
}

class Chopstick {
	private final int id;
	private boolean taken;

	public Chopstick(int id) {
		this.id = id;
	}

	public synchronized void take() throws InterruptedException {
		while (taken) {
			wait();
		}
		taken = true;
	}

	public synchronized void drop() {
		taken = false;
		notifyAll();
	}

	@Override
	public String toString() {
		return "Chopstick " + id;
	}
}

class ChopsticksQueue extends LinkedBlockingQueue<Chopstick> {

}

/**
 * 筷子盒
 */
class ChopstickBin {
	private ChopsticksQueue bin = new ChopsticksQueue();

	public Chopstick get() throws InterruptedException {
		return bin.take();
	}

	public void put(Chopstick chopstick) throws InterruptedException {
		bin.put(chopstick);
	}
}

class Philosopher implements Runnable {
	private static Random rand = new Random(47);
	private final int id;
	private final int ponderFactor;
	private ChopstickBin bin;

	public Philosopher(int id, int ponderFactor, ChopstickBin bin) {
		this.id = id;
		this.ponderFactor = ponderFactor;
		this.bin = bin;
	}

	private void pause() throws InterruptedException {
		if (ponderFactor == 0) return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Print.print(this + " " + "thinking");
				pause();
				// 从筷子盒里面取出一只
				Chopstick c1 = bin.get();
				Print.print(this + " has " + c1 + " waiting for another one");
				// 再取一只
				Chopstick c2 = bin.get();
				Print.print(this + " has " + c2);
				Print.print(this + " eating");
				pause();
				// Put the chopsticks back in bin.
				bin.put(c1);
				bin.put(c2);
			}
		} catch (InterruptedException e) {
			Print.print(this + " " + "exiting via interrupt");
		}
	}

	public String toString() {
		return "Philosopher " + id;
	}
}
