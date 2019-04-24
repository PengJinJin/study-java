package com.java.concurrency;

import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {

	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		return "result of CallableDemo " + id;
	}
}

public class CallableDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> result = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			result.add(exec.submit(new TaskWithResult(i)));
		}
		for (Future<String> fs : result) {
			try {
				System.out.println(fs.isDone() + "==" + fs.get() + " " + fs.isDone());
			} catch (InterruptedException | ExecutionException e) {
				System.out.println(e);
				return;
			} finally {
				exec.shutdown();
			}
		}
	}

}

