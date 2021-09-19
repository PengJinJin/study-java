package root.com.java.test.stream.test;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import root.com.java.test.enums.CaloricLevel;
import root.com.java.test.enums.Type;
import root.com.java.test.stream.bean.Dish;
import root.com.java.test.stream.bean.Trader;
import root.com.java.test.stream.bean.Transaction;

public class Test2 {

	public static void t1() {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));
		// 找出2011年的所有交易并按交易额排序（从低到高）
		List<Transaction> list2011 = transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted(comparing(Transaction::getValue)).collect(toList());
		list2011.forEach(f -> System.out.println(f.toString()));
		System.out.println("==================");
		// 交易员都在哪些不同的城市工作过
		// 1
		List<String> cities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
		cities.forEach(System.out::println);
		// 2
		Set<String> cities2 = transactions.stream().map(t -> t.getTrader().getCity()).collect(toSet());
		cities2.forEach(System.out::println);
		System.out.println("==================");
		// 查找所有来自于剑桥的交易员，并按姓名排序
		List<Trader> listNames = transactions.stream().map(Transaction::getTrader)
				.filter(t -> "Cambridge".equals(t.getCity())).distinct().sorted(comparing(Trader::getName))
				.collect(toList());
		listNames.forEach(n -> System.out.println(n.toString()));
		System.out.println("==================");
		// 返回所有交易员的姓名字符串，按字母顺序排序
		// 1(效率并不高)
		String words = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("",
				(a, b) -> a + b);
		System.out.println(words);
		// 2(效率比较高)
		String words2 = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted()
				.collect(joining(","));
		System.out.println(words2);
		System.out.println("==================");
		// 有没有交易员是在米兰工作的
		boolean isMilan = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
		System.out.println(isMilan);
		System.out.println("==================");
		// 打印生活在剑桥的交易员的所有交易额
		transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue)
				.collect(toList()).forEach(System.out::println);
		// 生活在剑桥的交易员的所有交易额之和
		// 1.它有一个暗含的装箱成本。每个 Integer 都必须拆箱成一个原始类型，再进行求和
		Integer valueSum = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue).reduce(Integer::sum).orElse(0);
		System.out.println("valueSum=====" + valueSum);
		// 2.mapToInt返回IntStream， IntStream 还支持其他的方便方法，如max 、 min 、 average 等
		Integer valueSum2 = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
				.mapToInt(Transaction::getValue).sum();
		System.out.println("valueSum2=====" + valueSum2);
		System.out.println("==================");
		// 所有交易中，最高的交易额是多少
		Integer maxVal = transactions.stream().map(Transaction::getValue).reduce(Integer::max).orElse(0);
		System.out.println(maxVal);
		System.out.println("==================");
		// 找到交易额最小的交易
		Integer minVal = transactions.stream().map(Transaction::getValue).min(Integer::compare).orElse(0);
		System.out.println(minVal);
		System.out.println("==================");
	}

	/**
	 * 文件的单词个数
	 */
	public static void t2() {
		long uniqueWords = 0;
		try (Stream<String> lines = Files.lines(Paths.get("D://data.txt"), Charset.defaultCharset())) {
			// lines.flatMap(line -> Arrays.stream(line.split("
			// "))).forEach(System.out::print);
			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).count();
			System.out.println(uniqueWords);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 由函数生成流：创建无限流(Stream.iterate)
	 */
	public static void t3() {
		// Stream.iterate生成斐波纳契数列
		Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] }).limit(15).map(t -> t[0])
				.forEach(System.out::println);
		// Stream.generate
		IntSupplier fib = new IntSupplier() {
			private int previous = 0;
			private int current = 1;

			public int getAsInt() {
				int oldPrevious = previous;
				int nextValue = previous + current;
				previous = current;
				current = nextValue;
				return oldPrevious;
			}
		};
		IntStream.generate(fib).limit(10).forEach(System.out::println);
	}

	/**
	 * 归约和汇总
	 */
	public static void t4() {
		List<Dish> menu = Dish.getList();
		// 计数
		long howManyDishes = menu.stream().collect(counting());
		System.out.println(howManyDishes);
		System.out.println("=====================");
		// 查找流中的最大值和最小值
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
		Optional<Dish> leastCalorieDish = menu.stream().collect(minBy(dishCaloriesComparator));
		System.out.println(mostCalorieDish.get().getCalories() + ", " + leastCalorieDish.get().getCalories());
		System.out.println("=====================");
		// 汇总
		// 菜单列表的总热量
		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
		System.out.println(totalCalories);
		System.out.println("=====================");
		// 平均数
		double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));
		System.out.println(avgCalories);
		System.out.println("=====================");
		// summarizingInt这个收集器的作用是:count,sum,min,max,average
		IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println(menuStatistics);
		System.out.println("=====================");
		// 使用reducing
		int totalCalories1 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
		System.out.println(totalCalories1);
		System.out.println("=====================");
		int totalCalories2 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).orElse(0);
		int totalCalories3 = menu.stream().mapToInt(Dish::getCalories).sum();
	}

	/**
	 * 用 reducing 连接字符串
	 */
	public static void t5() {
		List<Dish> menu = Dish.getList();
		// 虽然可以用 reducing 连接字符串,但从可读性或是性能方面考虑，使用 joining 收集器比较合适
		String shortMenu = menu.stream().map(Dish::getName).collect(joining());
		// 用 reducing 连接字符串
		String shortMenu1 = menu.stream().map(Dish::getName).collect(reducing((n1, n2) -> n1 + n2 + ",")).orElse(null);
		String shortMenu2 = menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2));
		System.out.println(shortMenu);
		System.out.println(shortMenu1);
		System.out.println(shortMenu2);
	}

	/**
	 * 分组
	 */
	public static void t6() {
		List<Dish> menu = Dish.getList();
		// 类型分组
		System.out.println("类型分组=====================");
		Map<Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(dishesByType);
		dishesByType.forEach((k, v) -> {
			System.out.println(BigDecimal.ZERO + "++++");
		});
		System.out.println("=====================");
		// 热量分组
		System.out.println("热量分组=====================");
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(groupingBy(dish -> {
			if (dish.getCalories() <= 400) {
				return CaloricLevel.DIET;
			} else if (dish.getCalories() >= 700) {
				return CaloricLevel.FAT;
			}
			return CaloricLevel.NORMAL;
		}));
		System.out.println(dishesByCaloricLevel);
		System.out.println("=====================");
		// 多级分组
		System.out.println("多级分组=====================");
		Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
				.collect(groupingBy(Dish::getType, groupingBy(dish -> {
					if (dish.getCalories() <= 400) {
						return CaloricLevel.DIET;
					} else if (dish.getCalories() >= 700) {
						return CaloricLevel.FAT;
					}
					return CaloricLevel.NORMAL;
				})));
		System.out.println(dishesByTypeCaloricLevel);
		System.out.println("=====================");
	}

	/**
	 * 按子组收集数据
	 */
	public static void t7() {
		List<Dish> menu = Dish.getList();
		// 按子组收集数据
		System.out.println("按子组收集数据=====================");
		Map<Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
		System.out.println(typesCount);
		System.out.println("=====================");
		// 把收集器的结果转换为另一种类型
		Map<Type, Optional<Dish>> mostCaloricByType = menu.stream()
				.collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
		// step1.maxBy生成的类型是Optional
		System.out.println(mostCaloricByType);// {FISH=Optional[salmon], OTHER=Optional[pizza], MEAT=Optional[pork]}
		// step2.把收集器的结果转换为另一种类型
		// collectingAndThen方法接受两个参数——要转换的收集器以及转换函数，并返回另一个收集器
		Map<Type, Dish> mostCaloricByType2 = menu.stream().collect(
				groupingBy(Dish::getType, collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get)));
	}

	public static void main(String[] args) {
		t6();
		/*
		Double d1 = new Double(45.23);
		Double d2 = new Double(45.22);
		int dou = d1.compareTo(d2);
		System.out.print(dou);
		System.out.print(d1 + d2);
		System.out.print(Double.compare(d1, d2));
		*/
	}

}
