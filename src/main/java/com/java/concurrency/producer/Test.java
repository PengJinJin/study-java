package com.java.concurrency.producer;

public class Test {

	public static void main(String[] args) {
		Person person = new Person();
		new Thread(new Producer(person)).start();
		new Thread(new Consumer(person)).start();
	}

}
