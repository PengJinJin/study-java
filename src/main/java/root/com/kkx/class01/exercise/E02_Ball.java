package root.com.kkx.class01.exercise;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class E02_Ball {

	public static void main(String[] args) {
		ball();
		getStringLength();
		getIntLength();
	}

	/**
	 * 双色球 红球33选6，蓝球16选1
	 */
	private static void ball() {
		Random ran = new Random();
		int blueBall = ran.nextInt(16) + 1;
		System.out.println("蓝球：" + blueBall);
		int[] array = new int[6];
		for (int i = 0; i < array.length; i++) {
			array[i] = ran.nextInt(33) + 1;
			for (int j = 0; j < i; j++) {
				if (array[i] == array[j]) {
					i--;
					break;
				}
			}
		}

		System.out.println("红球:" + Arrays.toString(array));
	}

	/**
	 * （当字符串处理）从控制台输入一个整数，然后输出这个数是几位的。 如:输入 12345 输出 5
	 */
	private static void getStringLength() {
		System.out.println("请输入一个数");
		Scanner scan = new Scanner(System.in);
		String a = scan.next();
		System.out.println(a.length());
	}

	/**
	 * 从控制台输入一个整数，然后输出这个数是几位的。 如:输入 12345 输出 5
	 */
	private static void getIntLength() {
		System.out.println("请输入一个整数");
		Scanner scan = new Scanner(System.in);
		long a = scan.nextLong();
		long res = 0;
		if (a == 0) {
			res = 1;
		} else {
			while (a > 0) {
				a = a / 10;
				res = res + 1;
			}
		}
		System.out.println(res);
	}

}
