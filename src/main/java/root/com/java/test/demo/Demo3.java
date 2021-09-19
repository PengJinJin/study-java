package root.com.java.test.demo;

public class Demo3 {

	public static void main(String[] args) {
		t1();
	}

	static void t3() {
		int array[] = { 4, 1, 3, 2, 54, 76, 12, 90, 100 };
		int temp;
		for (int i = 0; i < array.length - 1; i++) {
			System.out.println("第" + (i + 1) + "次外层循环========");
			for (int j = i; j < array.length - 1 - i; j++) {
				// 打印 左边和右边的值
				System.out.println(array[j] + " == " + array[j + 1]);
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
			System.out.println("第" + (i + 1) + "次外层循环结束后排序：");
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[j] + "  ");
				System.out.println();
			}
		}
	}

	static void t2() {
		int[] array = { 23, 32, 12, 44, 8 };
		for (int i = 0; i < array.length - 1; i++) {
			System.out.println("第" + (i + 1) + "次外层循环========");
			for (int j = 0; j < array.length - 1 - i; j++) {
				int a = array[j];
				int b = array[j + 1];
				// 打印 a 和 b 的值
				System.out.println(a + " == " + b);
				if (a > b) {
					array[j] = b;
					array[j + 1] = a;
				}
			}
			System.out.println("第" + (i + 1) + "次循环结束后排序：");
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[j] + "  ");
				System.out.println();
			}
		}

		for (int i : array) {
			System.out.print(i + "  ");
		}
	}

	static void t1() {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= i; j++) {
				int result = i * j;
				System.out.print(j + " * " + i + " = " + result);
				System.out.print("\t");
			}
			System.out.println();
		}
	}

}
