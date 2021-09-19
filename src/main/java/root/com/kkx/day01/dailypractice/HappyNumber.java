package root.com.kkx.day01.dailypractice;

import java.util.Scanner;

public class HappyNumber {
	public static boolean rlue(int num) {
		if (num / 100 > 9 || num == 0) {
			System.out.println("请输入三位以内的正整数");
			return false;
		}
		int uNum = num % 100 % 10;
		int tNum = num % 100 / 10;
		int hNum = num / 100;
		int sum = uNum * uNum + tNum * tNum + hNum * hNum;
		if (sum == 1) {
			System.out.println("1");
			return true;
		} else if (sum == 4 || sum == 16 || sum == 37 || sum == 58
				|| sum == 89 || sum == 145 || sum == 42 || sum == 20) {
			System.out.println("-1");
			return false;
		} else
			return rlue(sum);
	}

	public static void main(String[] args) {
		System.out.println("请输入快乐数");
		Scanner sca = new Scanner(System.in);
		int num = sca.nextInt();
		rlue(num);
	}
}
