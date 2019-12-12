package com.design_pattern.decorator;

// 具体装饰
public class ConDecorator extends AbsDecorator {

	public ConDecorator(AbsComponent component) {
		super(component);
	}

	@Override
	public void method() {
		System.out.println("ok..");
		super.method();
		System.out.println("finish..");
	}
}
