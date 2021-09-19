package root.com.java.test.demo;

import java.util.Scanner;

public class Demo2 {

	private static Scanner input;

	public static void main(String[] args) {
		try {
			input = new Scanner(System.in);
			System.out.println("请输入学生个数");
			int aa = input.nextInt();
			System.out.println("请输课程数");
			int ss = input.nextInt();
			int[][] arr = new int[aa][ss];

			for (int i = 0; i < aa; i++) {
				for (int j = 0; j < ss; j++) {
					System.out.println("请输入该生的第" + (i + 1) + "个学生第" + (j + 1) + "门功课成绩");
					arr[i][j] = input.nextInt();
				}
			}

			add(arr);// 调用
			average(arr);
			getMax(arr);
			getMin(arr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != input) {
				input.close();
			}
		}
	}

	/**
	 * 每个学生的求总分 求和
	 * 
	 * @param arr
	 */
	public static void add(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr[i].length; j++) {
				sum = sum + arr[i][j];
			}
			System.out.println("您输入的第" + (i + 1) + "个学生的总分sum=" + sum);
		}
	}

	/**
	 * 求平均值
	 * 
	 * @param arr
	 */
	public static void average(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = 0; j < arr[i].length; j++) {
				sum = sum + arr[i][j];
			}
			int average = sum / arr[i].length;
			System.out.println("您输入的第" + (i + 1) + "个学生的平均分average=" + average);
		}
	}

	/**
	 * 获取最大值
	 * 
	 * @param arr
	 */
	public static void getMax(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			int max = arr[i][0];
			for (int j = 1; j < arr[i].length; j++) {
				if (arr[i][j] > max) {
					max = arr[i][j];
				}
			}
			System.out.println("您输入的第" + (i + 1) + "个学生的最高分max=" + max);
		}
	}

	/**
	 * 获取最小值
	 * 
	 * @param arr
	 */
	public static void getMin(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			int min = arr[i][0];
			for (int j = 1; j < arr[i].length; j++) {
				if (arr[i][j] < min) {
					min = arr[i][j];
				}
			}
			System.out.println("您输入的第" + (i + 1) + "个学生的最低分min=" + min);
		}

	}
}
