package com.design_pattern.adpter;

// 适配器
public class ClassAdapter extends Adaptee implements Target {

	@Override
	public void method2() {
//		method1();
		System.out.println("Class Adaptee Method2");
	}

}
