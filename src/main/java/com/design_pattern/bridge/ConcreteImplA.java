package com.design_pattern.bridge;

// 具体实现A
public class ConcreteImplA implements Implementor {
	@Override
	public void method() {
		System.out.println("this is A");
	}
}
