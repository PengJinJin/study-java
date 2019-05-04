package com.kkx.day05;

public class Ex03 {
	public static void main(String[] args) {
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 12; j++) {
				int x = 36 - i - j;
				if (i * 4 + j * 3 + x / 2 == 36 && x % 2 == 0) {
					System.out.println("男:" + i + "女:" + j + "小孩:" + x);
				}
			}
		}
	}
}
