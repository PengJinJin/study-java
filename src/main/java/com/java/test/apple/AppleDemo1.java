package com.java.test.apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.java.test.vo.Apple;

public class AppleDemo1 {

	public static void main(String[] args) {
		// test1();
		// test2();
		test4();
	}

	private static void test4() {
		List<String> strList = Arrays.asList("", "a", "", "b", "", ".");
		Predicate<String> notEmpty = (String s) -> !s.isEmpty();
		List<String> res = filter(strList, notEmpty);
		res.forEach(System.out::println);
	}

	private static void test3() {
		List<Apple> list = Apple.getAppleList();
		List<Apple> r1 = filter(list, (Apple apple) -> apple.getWeight() > 150);
		r1.forEach(a -> System.out.println(a.toString()));
		List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		List<Integer> r2 = filter(numbers, (Integer i) -> i % 2 == 0);
		r2.forEach(i -> System.out.print(i + "\t"));
	}

	/**
	 * ��Ϊ������
	 * 
	 */
	// private static void test1() {
	// List<Apple> list = Apple.getAppleList();
	// List<Apple> result = filterApples(list, new AppleGreenColorPredicate());
	// List<Apple> result2 = filterApples(list, new
	// AppleHeavyWeightPredicate());
	// result.forEach(a -> System.out.print(a.toString()));
	// System.out.print("==============");
	// result2.forEach(a -> System.out.print(a.toString()));
	// }

	/**
	 * Lambda ���ʽ
	 */
	// private static void test2() {
	// List<Apple> list = Apple.getAppleList();
	// List<Apple> result = filterApples(list, (Apple apple) ->
	// "red".equalsIgnoreCase(apple.getColor()));
	// result.forEach(a -> System.out.print(a.toString()));
	// }

	// private static List<Apple> filterApples(List<Apple> inventory,
	// ApplePredicate p) {
	// List<Apple> result = new ArrayList<>();
	// for (Apple apple : inventory) {
	// if (p.test(apple)) {
	// result.add(apple);
	// }
	// }
	// return result;
	// }

	/**
	 * ����List ����(�ʺ϶��ֳ����Ͷ������͵�List)
	 * <p>
	 * �о�������Ƚ�����������ɸѡ
	 * </p>
	 * 
	 * @param list
	 *            ԭʼ�������
	 * @param p
	 *            ����
	 * @return
	 */
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T t : list) {
			if (p.test(t)) {
				result.add(t);
			}
		}
		return result;
	}

}
