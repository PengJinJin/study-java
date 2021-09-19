package root.com.java.test.apple;

import java.util.Comparator;
import java.util.List;

import root.com.java.test.vo.Apple;

public class AppleDemo2 {

	public static void main(String[] args) {
		test3();
	}

	private static void test1() {
		List<Apple> list = Apple.getAppleList();
		list.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
		list.forEach(a -> System.out.println(a.toString()));
	}

	private static void test2() {
		Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
	}
	
	private static void test3() {
		Function<Integer, Apple> c2 = Apple::new;
		Apple a1 = c2.apply(120);
		a1.setColor("asdasdasd");
		System.out.println(a1.toString());
	}

}
