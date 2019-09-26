package com.lambda;

import org.junit.Test;

import java.util.function.Consumer;

public class ConsumerTest {

	public void apply(double num, Consumer<Double> consumer) {
		consumer.accept(num);
	}

	@Test
	public void testConsumer() {
		apply(100, num -> System.out.println("消费了" + num));
	}

}
