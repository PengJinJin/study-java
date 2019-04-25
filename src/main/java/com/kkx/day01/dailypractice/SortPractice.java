package com.kkx.day01.dailypractice;

import java.util.Arrays;

public class SortPractice {
	public static void main(String[] args) {
		int[] a1 = {3, 8, 6, 5};
		int[] a2 = {7, 4, 9, 0, 4};
		//directInsertionSort(a1, a2);
		//halfInsertedSort(a1, a2);
		//bubbleSort(a1,a2);
		//selectSort(a1, a2);
		quickSort(a1, a2);
	}

	/**
	 * 直接插入排序,有两个有序数组，合并成一个有序数组，例如：arr1={3,8,6,5} arr2={7,4，9，0，4} 合并后为 {3，8，6，5，7，4，9，0，4} ,要求时间复杂度最小
	 *
	 * @param a1
	 * @param a2
	 */
	public static void directInsertionSort(int[] a1, int[] a2) {
		int c[] = new int[a1.length + a2.length];
		for (int i = 0; i < a1.length; i++) {
			c[i] = a1[i];
		}
		for (int i = 0; i < a2.length; i++) {
			c[i + a1.length] = a2[i];
		}
		System.out.println("排序前：" + Arrays.toString(c));
		for (int i = 1; i < c.length; i++) {
			if (c[i] < c[i - 1]) {
				int temp = c[i];
				int j = i - 1;
				for (j = i - 1; j >= 0 && temp < c[j]; --j) {
					c[j + 1] = c[j];
				}
				System.out.println(Arrays.toString(c));
				c[j + 1] = temp;

			}
		}
		System.out.println("排序后：" + Arrays.toString(c));
	}

	/**
	 * 折半插入排序。直接插入排序,有两个有序数组，合并成一个有序数组
	 *
	 * @param a1
	 * @param a2
	 */

	public static void halfInsertedSort(int[] a1, int[] a2) {
		int c[] = new int[a1.length + a2.length];
		for (int i = 0; i < a1.length; i++) {
			c[i] = a1[i];
		}
		for (int i = 0; i < a2.length; i++) {
			c[i + a1.length] = a2[i];
		}
		System.out.println("排序前" + Arrays.toString(c));
		for (int i = 1; i < c.length; i++) {
			int low = 0;
			int high = i - 1;
			int temp = c[i];
			while (low <= high) {
				int mid = (low + high) / 2;
				if (c[mid] > temp) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			for (int j = i - 1; j >= 0 && j >= high + 1; --j) {
				c[j + 1] = c[j];
			}
			c[high + 1] = temp;
		}
		System.out.println(Arrays.toString(c));

	}

	/**
	 * 冒泡排序
	 *
	 * @param a1
	 * @param a2
	 */
	public static void bubbleSort(int[] a1, int[] a2) {
		int[] c = new int[a1.length + a2.length];
		for (int i = 0; i < a1.length; i++) {
			c[i] = a1[i];
		}
		for (int i = 0; i < a2.length; i++) {
			c[i + a1.length] = a2[i];
		}
		System.out.println("排序前" + Arrays.toString(c));
		for (int i = 0; i < c.length; i++) {
			for (int j = c.length - 1; j > i; --j) {
				if (c[j] < c[j - 1]) {
					int temp = c[j - 1];
					c[j - 1] = c[j];
					c[j] = temp;
				}

			}

		}
		System.out.println("排序后" + Arrays.toString(c));
	}

	/**
	 * 简单选择排序
	 *
	 * @param a1
	 * @param a2
	 */

	public static void selectSort(int[] a1, int[] a2) {
		int[] c = new int[a1.length + a2.length];
		for (int i = 0; i < a1.length; i++) {
			c[i] = a1[i];
		}
		for (int i = 0; i < a2.length; i++) {
			c[i + a1.length] = a2[i];
		}
		System.out.println("排序前" + Arrays.toString(c));
		for (int i = 0; i < c.length; i++) {
			int min = i;
			for (int j = i + 1; j < c.length; j++) {
				if (c[j] < c[min]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = c[i];
				c[i] = c[min];
				c[min] = temp;
			}

		}
		System.out.println("排序后" + Arrays.toString(c));
	}

	/**
	 * 快排
	 *
	 * @param a1
	 * @param a2
	 */
	public static void quickSort(int[] a1, int[] a2) {
		int[] c = new int[a1.length + a2.length];
		for (int i = 0; i < a1.length; i++) {
			c[i] = a1[i];
		}
		for (int i = 0; i < a2.length; i++) {
			c[i + a1.length] = a2[i];
		}
		System.out.println("排序前" + Arrays.toString(c));
		for (int i = 0; i < c.length; i++) {
			int low = i;
			int high = c.length - 1;
			int pivot = c[i];
			while (low < high) {
				while (low < high && c[high] >= pivot)
					--high;
				c[low] = c[high];
				while (low < high && c[low] <= pivot)
					++low;
				c[high] = c[low];
			}
			c[low] = pivot;
		}
		System.out.println("排序后" + Arrays.toString(c));
	}


}


