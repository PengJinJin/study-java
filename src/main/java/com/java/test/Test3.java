package com.java.test;

public class Test3 {

	public static void main(String[] args) {
		printMessage("hello", 30);
		System.out.println(456);
		System.out.println(123);
	}

	public static void printMessage(String message, Integer count) {
		Runnable r = () -> {
			for (int i = 0; i < count; i++) {
				System.out.println(message + "====" + i);
				Thread.yield();
			}
		};
		new Thread(r).start();
	}

}
