package root.com.java.concurrency.exercise;

import root.com.java.util.Print;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

public class E29_ToastOMatic2 {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue
						dryQueue = new ToastQueue(),
						butteredQueue = new ToastQueue(),
						toBeButteredQueue = new ToastQueue(),
						jammedQueue = new ToastQueue(),
						toBeJammedQueue = new ToastQueue(),
						finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Alternator(dryQueue, toBeButteredQueue, toBeJammedQueue));
		exec.execute(new Butterer(toBeButteredQueue, butteredQueue));
		exec.execute(new Jammer(toBeButteredQueue, jammedQueue));
		exec.execute(new Merger(butteredQueue, jammedQueue, toBeButteredQueue, toBeJammedQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow();
	}
}

class Toast {
	public enum Status {
		DRY, BUTTERED, JAMMED, READY {
			@Override
			public String toString() {
				return BUTTERED.toString() + "&" + JAMMED.toString();
			}
		}
	}

	private Status status = Status.DRY;
	private final int id;

	public Toast(int idn) {
		this.id = idn;
	}

	public void butter() {
		status = (status == Status.DRY) ? Status.BUTTERED : Status.DRY;
	}

	public void jam() {
		status = (status == Status.DRY) ? Status.JAMMED : Status.READY;
	}

	public int getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count;
	private Random rand = new Random(47);

	public Toaster(ToastQueue tq) {
		this.toastQueue = tq;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
				// Make toast
				Toast t = new Toast(count++);
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			Print.print("Toaster interrupted");
		}
		Print.print("Toaster off");
	}
}

// Apply butter to toast:
class Butterer implements Runnable {
	private ToastQueue inQueue, butteredQueue;

	public Butterer(ToastQueue inQueue, ToastQueue butteredQueue) {
		this.inQueue = inQueue;
		this.butteredQueue = butteredQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = inQueue.take();
				t.butter();
				Print.print(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			Print.print("Butterer interrupted");
		}
		Print.print("Butterer off");
	}
}

// Apply jam to toast:
class Jammer implements Runnable {
	private ToastQueue inQueue, jamQueue;

	public Jammer(ToastQueue inQueue, ToastQueue jamQueue) {
		this.inQueue = inQueue;
		this.jamQueue = jamQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = inQueue.take();
				t.jam();
				Print.print(t);
				jamQueue.put(t);
			}
		} catch (InterruptedException e) {
			Print.print("Jammer interrupted");
		}
		Print.print("Jammer off");
	}
}

// Consume the toast:
class Eater implements Runnable {
	private ToastQueue finishedQueue;

	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				// 验证状态
				if (t.getStatus() != Toast.Status.READY) {
					Print.print(">>>> Error: " + t);
					System.exit(1);
				} else {
					Print.print("Chomp! " + t);
				}
			}
		} catch (InterruptedException e) {
			Print.print("Eater interrupted");
		}
		Print.print("Eater off");
	}
}

// Outputs alternate inputs on alternate channels:
class Alternator implements Runnable {
	private ToastQueue inQueue, outQ1, outQ2;
	private boolean outTo;// 控制变化

	public Alternator(ToastQueue inQueue, ToastQueue outQ1, ToastQueue outQ2) {
		this.inQueue = inQueue;
		this.outQ1 = outQ1;
		this.outQ2 = outQ2;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = inQueue.take();
				if (!outTo) {
					outQ1.put(t);
				} else {
					outQ2.put(t);
				}
				outTo = !outTo;
			}
		} catch (InterruptedException e) {
			Print.print("Alternator interrupted");
		}
		Print.print("Alternator off");
	}
}

class Merger implements Runnable {
	private ToastQueue in1Queue, in2Queue, toBeButteredQueue, toBeJammedQueue, finishedQueue;

	public Merger(ToastQueue in1Queue, ToastQueue in2Queue, ToastQueue toBeButteredQueue,
								ToastQueue toBeJammedQueue, ToastQueue finishedQueue) {
		this.in1Queue = in1Queue;
		this.in2Queue = in2Queue;
		this.toBeButteredQueue = toBeButteredQueue;
		this.toBeJammedQueue = toBeJammedQueue;
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = null;
				while (t == null) {
					t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
					if (t != null) {
						break;
					}
					t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
				}
				switch (t.getStatus()) {
					case BUTTERED:
						toBeJammedQueue.put(t);
						break;
					case JAMMED:
						toBeButteredQueue.put(t);
						break;
					default:
						finishedQueue.put(t);
				}
			}
		} catch (InterruptedException e) {
			Print.print("Merger interrupted");
		}
		Print.print("Merger off");
	}
}
