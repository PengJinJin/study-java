package com.kkx.day01.dailypractice;

import java.util.Scanner;

public class FibonacciSequence {

	/**
	 * Java实现斐波那契数列，请使用递归思想
	 *
	 * @param n
	 * @return
	 */
	public static int rule(int n) {
		int fn = 1;
		if (n >= 3) {
			fn = rule(n - 1) + rule(n - 2);
		}
		return fn;
	}

	public static void main(String[] args) {
//		System.out.println(rule(8));
		Scanner sca = new Scanner(System.in);
		System.out.println("请输入数列的长度n");
		int a = sca.nextInt();
		System.out.println("斐波那契数列为");
		for (int i = 1; i <= a; i++) {
			System.out.print(rule(i) + "\t");
		}
	}

}
