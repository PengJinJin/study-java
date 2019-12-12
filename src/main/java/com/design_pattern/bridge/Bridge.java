package com.design_pattern.bridge;

// 具体桥
public class Bridge extends AbstractBridge {
	@Override
	public void method() {
		getImpl().method();
	}
}
