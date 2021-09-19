package root.com.java.test.demo;

import java.util.Arrays;
import java.util.Scanner;

public class Demo1 {

	public static void main(String[] args) {
		t1();
	}

	public static void t6() {
		Scanner s = new Scanner(System.in);
		int row = s.nextInt();
		int[][] arr = new int[row][];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new int[i + 1];
			for (int j = 0; j < arr.length; j++) {
				if (j == 0 || j == i) {
					arr[i][j] = 1;
				} else {
					if (i >= 1) {
						arr[i][j] = arr[i - 1][j] + arr[i - 1][j - 1];	
					}
				}
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void t5() {
		int[] a;
		// 创建并初始化一个一维数组
		int[] array = new int[] { 31, 21, 123, 23, 456, 12, 654, 13 };
		// 输出他们的下标和对应的值
		for (int i = 0; i < array.length; i++) {
			System.out.println("数组array第" + i + "个下标对应的值是" + array[i]);
		}
	}

	public static void t4() {
		StringBuffer buffer = new StringBuffer("StringBuffer");
		buffer.delete(0, 6);
		System.out.println(buffer);
	}

	public static void t3() {
		String s = "boo:and:foo";
		System.out.println(Arrays.asList(s.split(":", 2)));
		System.out.println(Arrays.asList(s.split(":", 5)));
		System.out.println(Arrays.asList(s.split(":", 0)));
		System.out.println(Arrays.asList(s.split(":", -2)));
		System.out.println(Arrays.asList(s.split("o", 2)));
		System.out.println(Arrays.asList(s.split("o", 5)));
		System.out.println(Arrays.asList(s.split("o", 0)));
		System.out.println(Arrays.asList(s.split("o", -2)));
	}

	public static void t2() {

		String str = "很高兴认识高高你";
		String str1 = "很高兴认识你";
		String str2 = "aBcDeF";
		// str.replace(target, replacement)
		System.out.println(str2.compareTo("abcdef"));
		// System.out.print(str2.split(regex));
//		System.out.print(str.replace('高', 'g'));
		System.out.println(str.substring(1, 3));
	}

	public static void t1() {
		int x = 5;
		// 上半区
		for (int i = 1; i <= x; i++) {
			// 空格
			for (int j = 0; j < x - i; j++) {
				System.out.print(" ");
			}
			// ****
			for (int j = 1; j <= 2 * i - 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		// 下半区
		for (int i = 1; i <= x - 1; i++) {
			// 空格
			for (int j = 1; j <= i; j++) {
				System.out.print(" ");
			}
			// ****
			for (int j = 0; j < 2 * x - 1 - 2 * i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
