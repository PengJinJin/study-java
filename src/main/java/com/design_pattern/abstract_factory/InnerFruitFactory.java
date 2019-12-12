package com.design_pattern.abstract_factory;

// 具体工厂
public class InnerFruitFactory implements FruitFactory {
	@Override
	public Fruit getApple() {
		return new InnerApple();
	}

	@Override
	public Fruit getBanana() {
		return new InnerBanana();
	}
}
