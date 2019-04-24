package com.java.concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.java.util.Print.print;

/**
 * 创建一个辐射计数器
 * 它可以具有任意数量的传感器
 */
public class E17_RadiationCounter {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Sensor(i));
		}
		TimeUnit.SECONDS.sleep(3);
		Sensor.cancel();
		exec.shutdown();
		if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			print("还没有终止任务");
		}
		print("总数: " + Sensor.getTotalCount());
		print("Sum of Sensors: " + Sensor.sumSensor());
	}

}


class Count {
	private int count = 0;
	private Random random = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (random.nextBoolean()) {
			Thread.yield();
		}
		return count = ++temp;
	}

	public synchronized int value() {
		return count;
	}
}

class Sensor implements Runnable {
	private static Random rand = new Random(47);
	private static Count count = new Count();
	private static List<Sensor> sensors = new ArrayList<>();
	private static volatile boolean canceled = false;
	private final int id;
	private int number;

	public static void cancel() {
		canceled = true;
	}

	public Sensor(int id) {
		this.id = id;
		sensors.add(this);
	}

	@Override
	public void run() {
		while (!canceled) {
			if (rand.nextInt(5) == 0) {
				synchronized (this) {
					++number;
				}
				print(this + " Total: " + count.increment());
//				count.increment();
			}
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized int getValue() {
		return number;
	}

	@Override
	public String toString() {
		return "Sensor " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumSensor() {
		int sum = 0;
		for (Sensor sensor : sensors) {
			sum += sensor.getValue();
		}
		return sum;
	}


}
