package root.com.java.concurrency.exercise;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class FlowQueue<T> {
	private Queue<T> queue = new LinkedList<>();
	private int maxSize;

	public FlowQueue(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * 添加值到队列
	 */
	public synchronized void put(T v) throws InterruptedException {
		while (queue.size() >= maxSize) {
			wait();
		}
		queue.offer(v);// 添加一个元素到队尾
		maxSize++;
		notifyAll();
	}

	/**
	 * 从队列取值
	 */
	public synchronized T get() throws InterruptedException {
		while (queue.isEmpty()) {
			wait();
		}
		T returnVal = queue.poll();// 获取队首的元素，并从队列中移除
		maxSize--;
		notifyAll();
		return returnVal;
	}
}

class Item {
	private static int counter;
	private int id = counter++;

	@Override
	public String toString() {
		return "Item " + id;
	}
}

class Producer implements Runnable {
	private int delay;
	private FlowQueue<Item> output;

	public Producer(int delay, FlowQueue<Item> output) {
		this.delay = delay;
		this.output = output;
	}

	@Override
	public void run() {
		for (; ; ) {
			try {
				output.put(new Item());
				TimeUnit.MILLISECONDS.sleep(delay);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}

class Consumer implements Runnable {
	private int delay;
	private FlowQueue<?> input;

	public Consumer(int delay, FlowQueue<?> input) {
		this.delay = delay;
		this.input = input;
	}

	@Override
	public void run() {
		for (; ; ) {
			try {
				System.out.println(input.get());
				TimeUnit.MILLISECONDS.sleep(delay);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}

//{Args: 1 200}
public class E24_ProducerConsumer {
	public static void main(String[] args) throws InterruptedException {
		if (args.length < 2) {
			System.err.println("Usage java E24_ProducerConsumer" +
					" <producer sleep time> <consumer sleep time>");
			System.exit(1);
		}
		int producerSleep = Integer.valueOf(args[0]);
		int consumerSleep = Integer.valueOf(args[1]);
		FlowQueue<Item> fq = new FlowQueue<>(100);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Producer(producerSleep, fq));
		exec.execute(new Consumer(consumerSleep, fq));
		TimeUnit.SECONDS.sleep(2);
		exec.shutdownNow();
	}
}
