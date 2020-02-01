package com.design_pattern.bridge;

// 扩展抽象桥
public class Bridge extends AbstractBridge {
	@Override
	public void method() {
		getImpl().method();
	}
}
