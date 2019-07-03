package com.java.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static com.java.util.Print.print;

public class TestBlockQueues {

  static void getKey() {
	try {
	  new BufferedReader(new InputStreamReader(System.in)).readLine();
	} catch (IOException e) {
	  throw new RuntimeException();
	}
  }

  static void getKey(String message) {
    print(message);
    getKey();
  }

  static void test(String msg, BlockingQueue<LiftOff> queue) {
    print(msg);
	LiftOffRunner runner = new LiftOffRunner(queue);
	Thread t = new Thread(runner);
	t.start();
	for (int i = 0; i < 5; i++) {
	  runner.add(new LiftOff(5));
	}
	getKey("Press 'Enter' ("+msg+")");
	t.interrupt();
	print("Finished "+msg+" test");
  }

  public static void main(String[] args) {
    // unlimited size，这是一个无届队列
	test("LinkedBlockingQueue", new LinkedBlockingQueue<>());
	// Fixed size,ArrayBlockingQueue必须要指定尺寸
	test("ArrayBlockingQueue", new ArrayBlockingQueue<>(3));
	/// Size of 1
	test("SynchronousQueue", new SynchronousQueue<>());
  }

}

class LiftOffRunner implements Runnable {
  private BlockingQueue<LiftOff> rockets;

  public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
	this.rockets = rockets;
  }

  public void add(LiftOff lo) {
	try {
	  rockets.put(lo);
	} catch (InterruptedException e) {
	  print("InterruptedException during put()");
	}
  }

  @Override
  public void run() {
	try {
	  while (!Thread.interrupted()) {
		LiftOff rocket = rockets.take();
		rocket.run();
	  }
	} catch (InterruptedException e) {
	  print("Waking from take()");
	}
	print("Exiting LiftOffRunner");
  }
}
