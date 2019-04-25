package com.kkx.day01.homework;

import java.util.Scanner;

public class E04_BarCode {
	public static void main(String[] args) {
		System.out.println("校验码为：" + gen("736210283658"));
		System.out.println("是否为EAN_13条码：" + check("7362102836585"));
	}

	static void getCode() {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入13位的条形码");
		String code = scan.next();


	}

	/**
	 * 生成第13位校验码
	 *
	 * @param code
	 * @return
	 */
	public static int gen(String code) {
		int digit = calcDigit(code);

		// 分两种情况，个位为0或不为0
		int res;
		return (res = 10 - digit) == 10 ? 0 : res;
	}

	/**
	 * 检查是否EAN_13
	 *
	 * @param code EAN_13
	 * @return 如果是true，不是false
	 */
	public static boolean check(String code) {
		if (code.length() != 13) {
			return false;
		} else {
			boolean res = false;
			int last = Integer.valueOf(code.substring(12));// 取最后一位

			code = code.substring(0, code.length() - 1);// 只取前12位
			int digit = calcDigit(code);
			// 分两种情况，个位为0或不为0
			if (digit == 0 && last == 0) {
				res = true;
			} else if (digit + last == 10) {
				res = true;
			}
			return res;
		}
	}

	/**
	 * 计算结果的个位数
	 *
	 * @param code
	 * @return
	 */
	private static int calcDigit(String code) {
		int evenSum = 0;//偶数
		int oddSum = 0;// 奇数

		// 计算奇数位与偶数位的和
		for (int i = 1; i < code.length() + 1; i++) {
			// 取第i位的值
			int initial = Integer.valueOf(code.substring(i - 1, i));
			if (i % 2 == 0) {
				// even
				evenSum += initial;
			} else {
				// odd
				oddSum += initial;
			}
		}
		// oddSum + evenSum * 3
		int sum = oddSum + evenSum * 3;
		return sum % 10;
	}

}
