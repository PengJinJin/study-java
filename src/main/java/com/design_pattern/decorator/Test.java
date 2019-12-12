package com.design_pattern.decorator;

public class Test {
	public static void main(String[] args) {
		// 未被装饰的对象
		AbsComponent component = new ConComponentA();
		component.method();
		System.out.println("----------");

		// 装饰后的对象
		AbsComponent component1 = new ConDecorator(new ConComponentA());
		component1.method();
	}
}
