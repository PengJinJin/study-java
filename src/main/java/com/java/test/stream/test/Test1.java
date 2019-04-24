package com.java.test.stream.test;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.java.test.stream.bean.Dish;

/**
 * 排序,跳过,取前几个,扁平化map(flatMap),Optional,count,IntStream
 * 
 * @author knys
 *
 */
public class Test1 {

	public static void t1() {
		List<Dish> menu = Dish.getList();
		//
		Predicate<Dish> dishPredicate = d -> d.getCalories() > 300;
		List<String> threeHighCaloricDishNames = menu.stream().filter(dishPredicate.and(d -> !d.isVegetarian()))
				.sorted(comparing(Dish::getCalories).reversed()).limit(3).map(Dish::getName).collect(toList());

		List<String> skipThreeHighCaloricDishNames = menu.stream().filter(dishPredicate.and(d -> !d.isVegetarian()))
				.sorted(comparing(Dish::getCalories).reversed()).skip(3).map(Dish::getName).collect(toList());
		System.out.println(threeHighCaloricDishNames);
		System.out.println(skipThreeHighCaloricDishNames);
	}

	public static void t2() {
		List<String> words = Arrays.asList("Hello", "World");
		words.stream().map(word -> word.split("")).flatMap(Arrays::stream).distinct().collect(toList())
				.forEach(System.out::println);
	}

	public static void t3() {
		List<Dish> menu = Dish.getList();
		// flatMap用法
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		// flatMap把Stream<int[]>变成1,2,3这样的东东
		List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[] { i, j }))
				.collect(toList());

		// 检查谓词是否至少匹配一个元素，anyMatch 方法返回一个 boolean，因此是一个终端操作。
		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}
		// 检查谓词是否匹配所有元素
		boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
		// noneMatch 。 它可以确保流中没有任何元素与给定的谓词匹配
		boolean isHealthy2 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
	}

	public static void t4() {
		List<Dish> menu = Dish.getList();
		// Optional用法
		Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
		dish.ifPresent(d -> System.out.println(d.getName()));

		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream().map(x -> x * x).filter(x -> x % 3 == 0)
				.findFirst(); // 9
		firstSquareDivisibleByThree.ifPresent(i -> System.out.println(i));

		// 求和
		// int sum = someNumbers.stream().reduce(0, (a, b) -> a + b); =
		int sum = someNumbers.stream().reduce(0, Integer::sum);
		System.out.println(sum);

		// 计算所有菜单的热量
		int calories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
		System.out.println(calories);

		// 查找菜的个数
		int count = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
		System.out.println(count);
		long count1 = menu.stream().count();
		System.out.println(count1);
	}

	/**
	 * IntStream
	 */
	public static void t5() {
		List<Dish> menu = Dish.getList();
		// IntStream 上的操作只能 产生原始整数
		// 将 Stream 转 换为数值流
		IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
		// 将数值流转 换为 Stream
		Stream<Integer> stream = intStream.boxed();
		// 默认值 OptionalInt
		OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
		int max = maxCalories.orElse(1);
	}

	/**
	 * 数值范围
	 */
	public static void t6() {
		// IntStream 和 LongStream 的静态方法,range 和 rangeClosed: 第一个参数接受起始值，第二个参数接受结束值,
		// range 是不包含结束值的，而 rangeClosed 则包含结束值

		// 0-100的偶数(IntStream.range(1, 100)结果只有49,因为不包含结束值,也就是不包含100)
		IntStream evenNumbers = IntStream.rangeClosed(0, 100).filter(n -> n % 2 == 0);
		System.out.println(evenNumbers.count());// 50
	}

	public static void main(String[] args) {
		t8();
	}

	public static void t8() {
		Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
						.mapToObj(b -> new int[] { a, b, (int) Math.sqrt(a * a + b * b) }));
		pythagoreanTriples.limit(5).forEach(p -> System.out.println(p[0] + "," + p[1] + "," + p[2]));
		System.out.println("---------- ");
		Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
						.mapToObj(b -> new double[] { a, b, (double) Math.sqrt(a * a + b * b) })
						.filter(t -> t[2] % 1 == 0));
		pythagoreanTriples2.limit(5).forEach(p -> System.out.println(p[0] + "," + p[1] + "," + p[2]));
	}

	public static void t7() {
		/** 获取单词，并且去重 **/
		List<String> list = Arrays.asList("hello welcome", "world hello", "hello world", "hello world welcome");

		// map和flatmap的区别
		list.stream().map(item -> Arrays.stream(item.split(" "))).distinct().collect(toList())
				.forEach(System.out::println);
		System.out.println("---------- ");
		list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().collect(toList())
				.forEach(System.out::println);
	}

}
