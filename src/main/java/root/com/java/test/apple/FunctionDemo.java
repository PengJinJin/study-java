package root.com.java.test.apple;

import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import root.com.java.test.vo.Apple;

public class FunctionDemo {

	public static void main(String[] args) {
		t3();
	}

	private static void t2() {
		// List<Apple> list = Apple.getAppleList();
		// List<Integer> result = map(list, Apple::getWeight);
		// ConsumerDemo.forEach(result, System.out::print);

		List<Integer> weights = Arrays.asList(7, 3, 4, 10);
		List<Apple> apples = map(weights, Apple::new);
		ConsumerDemo.forEach(apples, apple -> {
			System.out.println(apple.toString());
		});
	}

	private static void t1() {
		List<Integer> list = map(Arrays.asList("abc", "d", "efg"), String::length);
		ConsumerDemo.forEach(list, System.out::println);
	}
	
	/**
	 * ��������
	 */
	private static void t3() {
		List<Apple> apples = Apple.getAppleList();
		// �������ݼ�����,����һ������ɫ
		apples.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
		// ����һ������(��ɫ��)
		Predicate<Apple> redApple = a -> a.getColor().equals("Red");
		// �ų���ɫ��,������������150
		Predicate<Apple> notRedApple = redApple.negate().and(a -> a.getWeight() > 150);
		// Ҫô��������150,Ҫô����ƻ��  a.or(b).and(c)���Կ��� (a || b) && c
		Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 150).or(a -> "Green".equals(a.getColor()));
		List<Apple> as = AppleDemo1.filter(apples, redAndHeavyAppleOrGreen);
		as.forEach(a -> System.out.println(a.toString()));
	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T t : list) {
			result.add(f.apply(t));
		}
		return result;
	}

}

@FunctionalInterface
interface Function<T, R> {
	R apply(T t);
}