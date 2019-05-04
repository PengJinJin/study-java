package com.kkx.day05;

public class Ex05 {
	public static void main(String[] args) {
		System.out.println(check("6923450657713"));
		//System.out.println(r);
	}

	static boolean check(String code) {
		if (code == null || code.length() != 13) {
			throw new RuntimeException("条形码必须13位");
		}
		int a=generator(code.substring(0, 12));
		int b = code.charAt(12)-'0';
		return a==b;
	}

	static int generator(String code) {
		if (code == null || code.length() != 12) {
			throw new RuntimeException("条形码必须12位");
		}
		int c1 = 0;
		int c2 = 0;
		for (int i = 0; i < code.length(); i += 2) {
			int a1 = code.charAt(i)-'0';
			int a2 = code.charAt(i + 1)-'0';
			c1 = c1 + a1;
			c2 = c2 + a2;
		}
		int cc = (c1 + c2 * 3) % 10;
		int r = (10 - cc)%10;
		return r;
	}
}
