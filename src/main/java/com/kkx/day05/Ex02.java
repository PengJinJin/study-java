package com.kkx.day05;

public class Ex02 {
	public static void main(String[] args) {

		System.out.println(calcMoney(18, 299));
	}

	static int calcMoney(int k, int time) {
		int money = 0;
		if (time < 150) {
			if (k <= 3) {
				money = 10;
			}
			if (k > 3 && k <= 15) {
				money = 10 + (k - 3) * 2;
			}
			if (k > 15) {
				money = 10 + 12 * 2 + (k - 15) * 3;
			}
		}
		if (time >= 150) {
			if (k <= 3) {
				money = 10 + (time / 150) * 1;
			}
			if (k > 3 && k <= 15) {
				money = 10 + (k - 3) * 2 + (time / 150) * 1;
			}
			if (k > 15) {
				money = 10 + 12 * 2 + (k - 15) * 3 + (time / 150) * 1;
			}
		}
		return money;
	}
}
