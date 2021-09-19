package root.com.java.concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class E11_RaceCondition {

	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("����ctrl-c�˳�");
		ExecutorService exec = Executors.newCachedThreadPool();
		Tank tank = new Tank();
		for (int i = 0; i < 10; i++) {
			exec.execute(new ConsistencyChecker(tank));
		}
		Thread.yield();
		exec.shutdown();
	}

}

class Tank {
	enum State {EMPTY, LOADED,}

	private State state = State.EMPTY;

	private int current_load = 0;

	public synchronized void validate() {
		if ((state == State.EMPTY && current_load != 0)
				|| (state == State.LOADED && current_load == 0)) {
			throw new IllegalStateException();
		}
	}

	public synchronized void fill() {
		state = State.LOADED;
		Thread.yield();// ����д����ʹ���ٷ���
		current_load = 10;
	}

	public synchronized void drain() {
		state = State.EMPTY;
		Thread.yield();
		current_load = 0;
	}

}

class ConsistencyChecker implements Runnable {

	private static Random random = new Random();
	private Tank tank;

	public ConsistencyChecker(Tank tank) {
		this.tank = tank;
	}

	@Override
	public void run() {
		for (; ; ) {
			if (random.nextBoolean()) {
				tank.fill();
			} else {
				tank.drain();
			}
			tank.validate();
		}
	}

}
