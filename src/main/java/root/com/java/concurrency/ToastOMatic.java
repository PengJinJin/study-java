package root.com.java.concurrency;

import root.com.java.util.Print;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static root.com.java.util.Print.print;

/**
 * LinkedBlockingQueue使用
 */
public class ToastOMatic {
	public static void main(String[] args) throws InterruptedException {
		ToastQueue dryQueue = new ToastQueue(),
						butteredQueue = new ToastQueue(),
						finishedQueue = new ToastQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Toaster(dryQueue));
		exec.execute(new Butterer(dryQueue, butteredQueue));
		exec.execute(new Jammer(butteredQueue, finishedQueue));
		exec.execute(new Eater(finishedQueue));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdown();
	}
}

class Toast {
	public enum Status {
		// 制作吐司
		DRY,
		// 抹黄油
		BUTTERED,
		// 涂果酱
		JAMMED
	}

	private Status status = Status.DRY;

	private final int id;

	public Toast(int id) {
		this.id = id;
	}

	public void butter() {
		status = Status.BUTTERED;
	}

	public void jammed() {
		status = Status.JAMMED;
	}

	public Status getStatus() {
		return status;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Toast " + id + ": " + status;
	}
}

class ToastQueue extends LinkedBlockingQueue<Toast> {

}

// 制作吐司任务
class Toaster implements Runnable {
	private ToastQueue toastQueue;
	private int count = 0;
	private Random random = new Random(47);

	public Toaster(ToastQueue toastQueue) {
		this.toastQueue = toastQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
				// Make toast
				Toast t = new Toast(count++);
				Print.print(t);
				// 加入任务队列
				toastQueue.put(t);
			}
		} catch (InterruptedException e) {
			Print.print("Toast InterruptedException");
		}
		Print.print("Toast off");
	}
}

class Butterer implements Runnable {
	private ToastQueue dryQueue, butteredQueue;

	public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
		this.dryQueue = dryQueue;
		this.butteredQueue = butteredQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = dryQueue.take();
				t.butter();
				Print.print(t);
				butteredQueue.put(t);
			}
		} catch (InterruptedException e) {
			Print.print("Butterer InterruptedException");
		}
		Print.print("Butterer off");
	}
}

class Jammer implements Runnable {
	private ToastQueue butteredQueue, finishedQueue;

	public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
		this.butteredQueue = butteredQueue;
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = butteredQueue.take();
				t.jammed();
				Print.print(t);
				finishedQueue.put(t);
			}
		} catch (InterruptedException e) {
			Print.print("Jammer InterruptedException");
		}
		Print.print("Jammer off");
	}
}

class Eater implements Runnable {
	private ToastQueue finishedQueue;
	private int count = 0;

	public Eater(ToastQueue finishedQueue) {
		this.finishedQueue = finishedQueue;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Toast t = finishedQueue.take();
				// 验证吐司是否有序，并且已经可以食用
				//verify that the toast is coming in order,and that all pieces are getting jammed
				if (t.getId() != count++ || t.getStatus() != Toast.Status.JAMMED) {
					Print.print(">>> Error: " + t);
					System.exit(1);
				} else {
					Print.print("Chomp! " + t);
				}
			}
		} catch (InterruptedException e) {
			Print.print("Eater InterruptedException");
		}
		Print.print("Eater off");
	}
}
