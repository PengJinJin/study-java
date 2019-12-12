package com.design_pattern.abstract_factory;

public class Test {
	public static void main(String[] args) {
		FruitFactory ff = new NorthFruitFactory();
		Fruit apple = ff.getApple();
		apple.get();
		Fruit banana = ff.getBanana();
		banana.get();
	}
}
