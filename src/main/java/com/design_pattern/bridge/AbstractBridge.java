package com.design_pattern.bridge;

// 抽象桥
public abstract class AbstractBridge {

	private Implementor impl;

	public abstract void method();

	Implementor getImpl() {
		return impl;
	}

	void setImpl(Implementor impl) {
		this.impl = impl;
	}

}
