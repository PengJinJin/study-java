package com.lambda;

public class LambdaTest {

	@FunctionalInterface
	interface Calculate {
		int operation(int value);
	}

	public static void main(String[] args) {
		LambdaTest test = new LambdaTest();
//		test.test1();
		test.test2();
	}

	public void test1() {
		int n = 3;

		Calculate calculate = value -> n + value;

		int i = calculate.operation(10);
		System.out.println(i);
	}

	private int count = 1;
	private static int num = 2;

	public void test2() {
		Calculate calculate = value -> {
			num = 10;
			count = 3;
			return num + value + count;
		};
		int i = calculate.operation(10);
		System.out.println(i);
	}

}
