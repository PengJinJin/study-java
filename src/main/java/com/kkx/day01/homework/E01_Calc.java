package com.kkx.day01.homework;

import java.util.Scanner;

public class E01_Calc {

	public static void main(String[] args) {
		//min();
		fare();
	}

	/**
	 * 从控制台输入5个数，然后求最小值
	 */
	public static void min() {
		Scanner scan = new Scanner(System.in);
		int[] array = new int[5];
		for (int i = 0; i < array.length; i++) {
			int a = scan.nextInt();
			array[i] = a;
		}
		int min = array[0];
		for (int j = 0; j < array.length; j++) {
			if (min > array[j]) {
				min = array[j];
			}
		}
		System.out.println(min);
	}


	/**
	 * 车费由两部分组成，
	 * 公里数和等候时间。0-3公里，收10元;4-15公里，
	 * 每公里2元;15公里以上的部分，每公里3元。
	 * 等候时间
	 * 每2分半收1元，不足2分半的不收费。不足1公里的按1公里计算。
	 * 车费是公里数钱+等候时间的钱。 比如:18公里299秒，应收车费:44 要求:输入公里数和秒数，输出车费。
	 */
	public static void fare() {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入公里数(正整数)：");
		int kilometers = sc.nextInt();
		System.out.println("输入时间(秒)：");
		int time = sc.nextInt();
		int perTime = time / 150;
		int fare = 0;
		while (kilometers > 0) {

			if (kilometers <= 3) {
				fare = 10 + perTime * 1;
				break;

			} else if (kilometers <= 15) {
				fare = 10 + (kilometers - 3) * 2 + perTime * 1;
				break;

			} else {
				fare = 10 + (kilometers - 15) * 3 + (kilometers - 15 - 3) * 2 + perTime * 1;
				break;
			}
		}
		System.out.println("车费" + fare);
	}

}


