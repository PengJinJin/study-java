package root.com.java.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class Tttt {

	public static void main(String[] args) {
		t4();
	}

	public static void t1() {

		// 声明一个int数组,用for和foreach分别打印出来
		int[] array = { 1, 2, 3, 5, 7 };
		// for
		for (int i = 0; i < array.length; i++) {
			int a = array[i];
			System.out.print(a);
			System.out.print("\t");
		}
		System.out.println();
		// foreach
		for (int i : array) {
			System.out.print(i);
			System.out.print("\t");
		}

	}

	public static void t2() {

		int x = 20;

		{// 复合语句开始
			int y = 40;
			System.out.println(y);
			int z = 245;
			boolean b;

			{// 复合语句开始
				b = y > z;
				System.out.println(b);
			} // 复合语句结束

		} // 复合语句结束

		/*
		 * 符合语句内的变量只有在改作用域内才能被访问 比如在这里访问不到b、y、z
		 */
//		System.out.print(b + "--" + y + "--" + z);
		String word = "Hello World";
		System.out.println(word);
	}

	public static void t3() {
		// 一次终止多层的示例
		int a = 2, b = 3;
		loop: for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				System.out.println("i = " + i + ", j = " + j);
				if (j == 2) {
					break loop;
				}
			}
		}
		// 这个语句正常应该是输出 2x3=6次
		// 加了break loop,循环到j=2时就跳出了
	}

	public static void t4() {
		int x = 8;
		for (int i = 0; i < x - 1; i++) {
			for (int j = x - 1; j > i; j--) {
				System.out.print(" ");
			}
			for (int j = 2 * x; j > 2 * x - 1 - 2 * i; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
		/*
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < i; j++) {
				System.out.printnb(" ");
			}
			for (int j = 0; j < 2 * x - 1 - 2 * i; j++) {
				System.out.printnb("*");
			}
			System.out.print();
		}
		*/
	}

}
