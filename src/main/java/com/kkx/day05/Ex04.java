package com.kkx.day05;

import java.util.Random;
import java.util.Scanner;

public class Ex04 {
	public static void main(String[] args) {
		Scanner sca = new Scanner(System.in);
		Random ran = new Random();
		int r = ran.nextInt(1000);
		int count = 0;
		System.out.println(r);
		do {
			int guess = sca.nextInt();
			if (guess > r) {
				System.out.println("太大了，再小点");

			} else if (guess < r) {
				System.out.println("太小了，再大点");
			} else {
				System.out.println("猜对了");
				return;
			}
			count++;
		} while (count < 10);
		System.out.println("太笨了");


	}
}
