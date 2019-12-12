package com.design_pattern.adpter.interfaces;

public class InAdapter extends InAdaptee {

	@Override
	public void method1() {
		System.out.println("interface adaptee method1");
	}

	@Override
	public void method2() {
		System.out.println("interface adaptee method2");
	}

}
