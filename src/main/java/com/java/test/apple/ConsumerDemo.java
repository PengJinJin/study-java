package com.java.test.apple;

import java.util.Arrays;
import java.util.List;

public class ConsumerDemo {

	public static void main(String[] args) {
		// t1();
		t2();
	}

	private static void t2() {
		validInput("Gosling", s -> System.out.println(s.length() > 3 ? "名字过长啦" : "名字正常"));
	}

	private static void t1() {
		forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));
	}

	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}

	public static <T> void validInput(T t, Consumer<T> c) {
		c.accept(t);
	}

}

@FunctionalInterface
interface Consumer<T> {
	void accept(T t);
}