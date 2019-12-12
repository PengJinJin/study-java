package com.design_pattern.factory_method;

public class Test {
	public static void main(String[] args) {
		AbstractFactory factory = new ConcreteFactory2();
		Product product = factory.createProduct();
		product.show();
	}
}
