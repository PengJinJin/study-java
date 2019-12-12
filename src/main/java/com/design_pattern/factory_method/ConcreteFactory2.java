package com.design_pattern.factory_method;

public class ConcreteFactory2 implements AbstractFactory {
	@Override
	public Product createProduct() {
		System.out.println("ConcreteFactory2 generator-->ConcreteProduct2...");
		return new ConcreteProduct2();
	}
}
