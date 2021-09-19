package root.com.kkx.day01.homework;

import java.util.Random;
import java.util.Scanner;

public class E03_Number {
	public static void main(String[] args) {
		//	number();
		//guessNumber();
		programmer();
	}

	/**
	 * 1-100的质数
	 */
	public static void number() {
		for (int i = 2; i <= 100; i++) {
			boolean flag = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag)
				System.out.println(i);
		}
	}

	/**
	 * 36个人搬36块砖，男人一个人搬4，女3，小孩2个人搬1块
	 * 所有的人一次搬完。几男几女几小孩?
	 */
	public static void programmer() {
		int men;
		int women;
		int child;
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 12; j++) {
				if (i * 4 + j * 3 + (36 - i - j) * 0.5 == 36) {
					System.out.println("男：" + i + "；女：" + j + "；小孩：" + (36 - i - j));

				}
			}
		}
	}


	/**
	 * 猜数游戏0到999的随机整数，猜十次
	 */

	public static void guessNumber() {
		Random ran = new Random();
		int number = ran.nextInt(1000);
		Scanner sc = new Scanner(System.in);
		int count = 0;
		while (true) {
			int guess = sc.nextInt();
			if (guess == number) {
				System.out.println("right");
				break;
			} else if (guess < number) {
				System.out.println("too small");
				count++;
			} else {
				System.out.println("too large");
				count++;
			}
			if (count == 10) {
				System.out.println("退出程序");
				System.out.println("正确的数：" + number);
				break;
			}
		}

	}

}
