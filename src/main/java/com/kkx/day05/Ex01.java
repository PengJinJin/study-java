package com.kkx.day05;

public class Ex01 {
	public static void main(String[] args) {
		int[] a = {1, 3, 88, 0, -3};
		int min=a[0];
		for (int b : a) {
			if (b< min) {
				min = b;
			}
		}
		System.out.println(min);
	}
}
