package com.kkx.day04.thred;

import java.util.concurrent.TimeUnit;

public class ThreadDemo4 {
	public static void main(String[] args) {
		Table t = new Table();
		Table.Person p1 = t.new Person();
		Table.Person p2 = t.new Person();
		p1.start();
		p2.start();
	}
}

class Table {
	private int bean = 20;

	int getBean() {
		if (bean == 0) {
			throw new RuntimeException("没有了");
		}
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return bean--;
	}

	class Person extends Thread {
		@Override
		public void run() {
			while (true) {
				int x = getBean();
//				Thread.yield();
				System.out.println(this.getName() + ": " + x);
			}
		}
	}


}
