package com.kkx.day04.thred;

public class ThredDemo {
	public static void main(String[] args) {
		Boo boo = new Boo();
		boo.start();
		Coo coo = new Coo();
		Thread aoo = new Thread(coo);
		aoo.start();
		new Thread(new Coo()).start();
		System.out.println("AAAAA");
	}

}

class Coo implements Runnable {
	@Override
	public void run() {
		for (int i = 10; i < 20; i++) {
			System.out.println(i);

		}
	}
}

class Boo extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);

		}
	}
}
