package com.design_pattern.factory_method;

public class ConcreteFactory1 implements AbstractFactory {

	@Override
	public Product createProduct() {
		System.out.println("ConcreteFactory1 generator-->ConcreteProduct1...");
		return new ConcreteProduct1();
	}
}
