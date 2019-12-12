package com.design_pattern.bridge;

// 具体实现B
public class ConcreteImplB implements Implementor {
	@Override
	public void method() {
		System.out.println("this is B");
	}
}
