package com.design_pattern.abstract_factory;

public class NorthFruitFactory implements FruitFactory {
	@Override
	public Fruit getApple() {
		return new NorthApple();
	}

	@Override
	public Fruit getBanana() {
		return new NorthBanana();
	}
}
