package com.java.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.java.test.vo.Apple;

public class Test1 {

	public static void main(String[] args) {
//		 test1();
		 test2();
		// test3();
		// test4();
//		 test5();
		// List<String> list = selectAreaForDrugsAreaPriceOfTreeNew("T");
		// list.forEach(System.out::print);
//		test4();
	}


	public static BigDecimal mapSum(List<BigDecimal> nums, Predicate<BigDecimal> p, Function<BigDecimal, BigDecimal> f) {
		return nums.stream().filter(p).map(f).reduce(BigDecimal::add).get();
	}
	
	public static void test1() {
		List<String> cities = new ArrayList<>();
		cities.add("Beijing");
		cities.add("Hubei");
		cities.add("Chongqing");
		cities.add("Shanghai");
		
		String city = cities.stream().filter(c -> c.equals("Shanghai") || c.equals("Chongqing")).collect(Collectors.joining(","));
		System.out.println(city);
		System.out.println(cities.contains("Hubei"));
	}

	public static void test2() {
		List<BigDecimal> prices = new ArrayList<BigDecimal>();
		prices.add(new BigDecimal("10"));
		prices.add(new BigDecimal("30"));
		prices.add(new BigDecimal("17"));
		prices.add(new BigDecimal("20"));
		prices.add(new BigDecimal("15"));
		prices.add(new BigDecimal("45"));
		prices.add(new BigDecimal("18"));
		prices.add(new BigDecimal("12"));
		System.out.println(mapSum(prices, (BigDecimal b) -> b.compareTo(BigDecimal.valueOf(15)) > 0, BigDecimal::abs));
		int sum = prices.stream().mapToInt(e -> Integer.valueOf(e.toString())).sum();
		System.out.println(sum);
		// ����20���9��,�ټ����ܺ�
		BigDecimal totalOfDiscountedPrices = prices.stream().filter(price -> price.compareTo(new BigDecimal("20")) > 0)
				.map(price -> price.multiply(BigDecimal.valueOf(0.9))).reduce(BigDecimal.ZERO, BigDecimal::add);
		System.out.println(totalOfDiscountedPrices);
	}

	public static void test3() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(10);
		numbers.add(20);
		numbers.add(30);
		numbers.add(40);
		numbers.add(50);
		int sum = numbers.stream().reduce(0, Integer::sum);
		System.out.println(sum);
	}

	public static void test4() {
		List<Apple> inventory = new ArrayList<>();
		inventory.add(new Apple("Green", 110));
		inventory.add(new Apple("Red", 160));
		inventory.add(new Apple("Yellow", 200));
		inventory.add(new Apple("Grey", 100));
		inventory.add(new Apple("Green", 170));

		// Map<String, List<Apple>> map = inventory.stream().filter(a ->
		// a.getWeight() > 100)
		//===============================================
		// .collect(Collectors.groupingBy(Apple::getColor));
		// Map<Apple, Long> map = inventory.stream().filter(a -> a.getWeight() >
		// 100)
		//===============================================
		// .collect(Collectors.groupingBy(Function.identity(),
		// Collectors.counting()));
		//===============================================
		Map<String, Long> map = inventory.stream().filter(a -> a.getWeight() > 100)
				.collect(Collectors.groupingBy(Apple::getColor, Collectors.counting()));
		//===============================================
		// map.forEach((k, v) -> System.out.print(k + "=====" + v));
		map.forEach((String k, Long v) -> {
			System.out.println(k + "===" + v);
			// v.forEach(System.out::print);
		});
		System.out.println("=========================");

		Map<String, List<Apple>> result = inventory.stream().collect(Collectors.groupingBy(apple -> apple.getColor()));
		result.forEach((String k, List<Apple> v) -> {
			System.out.println(k);
			v.forEach(System.out::println);
		});
		Stream<String> colors = inventory.stream().<String>map(a -> a.getColor());
		colors.forEach(System.out::println);

		// /*
		// ��ӡ������G��ͷ��
		inventory.stream().filter(apple -> apple.getColor().startsWith("G")).forEach(apple -> {
			System.out.println(apple.getColor() + "---" + apple.getWeight());
		});
		// ɸѡ�����е���ƻ��
		List<Apple> greenApple = inventory.stream().filter(apple -> "Green".equalsIgnoreCase((apple.getColor())))
				.collect(Collectors.toList());
		List<Apple> aps = inventory.stream().filter(Test1::isGreenApple).collect(Collectors.toList());
		System.out.println("=====");
		aps.forEach(System.out::println);
		System.out.println("=====");
		// */
		List<Apple> list = filterApples(inventory, (Apple a) -> "Green".equals(a.getColor()));
		list.forEach(apple -> {
			System.out.println(apple.getColor() + "---" + apple.getWeight());
		});
	}

	public static void test5() {
		Double[] dlist = { 2.0, 3.0, 2.1, 5.6, 1.11 };
		Double totalDouble = Arrays.asList(dlist).stream().reduce(new Double(0), Double::sum);
		System.out.println(totalDouble);
		// ��Double[]תΪList<String>
		List<String> castString = Arrays.asList(dlist).stream().map(d -> d.toString()).collect(Collectors.toList());
		// ��StringתΪBigDecimal�����
		BigDecimal totalBd = castString.stream().map(str -> new BigDecimal(str)).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		System.out.println(totalBd);
	}

	public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		result = inventory.stream().filter(apple -> p.test(apple)).collect(Collectors.toList());
		return result;
	}

	public static boolean isGreenApple(Apple apple) {
		return "green".equalsIgnoreCase(apple.getColor());
	}

	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}

	public static List<String> selectAreaForDrugsAreaPriceOfTreeNew(String areaType) {
		List<Apple> inventory = new ArrayList<>();
		inventory.add(new Apple("Green", 110));
		inventory.add(new Apple("Red", 160));
		inventory.add(new Apple("Yellow", 200));
		inventory.add(new Apple("Grey", 100));
		inventory.add(new Apple("Green", 170));
		List<String> selectedAreaCodeList = inventory.stream().map(drugsArea -> {
			if ("S".equals(areaType)) {
				return drugsArea.getColor();
			} else if ("T".equals(areaType)) {
				return String.valueOf(drugsArea.getWeight());
			}
			return areaType;
		}).collect(Collectors.toList());
		return (List<String>) selectedAreaCodeList;
	}

	public static List<Integer> filterSum(List<Integer> nums, Predicate<Integer> p) {
		return nums.stream().filter(n -> p.test(n)).collect(Collectors.toList());
	}

	public static void test6() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new Random().nextInt(100));
		}
		list.sort((i1, i2) -> i1 - i2);
		list.forEach(System.out::println);
		System.out.println("==========================");
		List<Integer> res = filterSum(list, (Integer i) -> i > 50);
		res.sort((i1, i2) -> i1 - i2);
		res.forEach(System.out::println);
	}

}
