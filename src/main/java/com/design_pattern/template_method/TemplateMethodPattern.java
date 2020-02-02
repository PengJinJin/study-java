package com.design_pattern.template_method;

public class TemplateMethodPattern {
	public static void main(String[] args) {
		AbstractClass abs = new ConcreteClass();
		abs.templateMethod();
	}
}

// 抽象类角色（接口也可以）
interface AbstractClass {

	// 模板方法
	default void templateMethod() {
		specificMethod();
		absM1();
		absM2();
	}

	// 具体方法
	default void specificMethod() {
		System.out.println(">>> Abstract Class specificMethod");
	}

	// 抽象方法1
	void absM1();

	// 抽象方法2
	void absM2();

}

// 具体子类角色
class ConcreteClass implements AbstractClass {

	@Override
	public void absM1() {
		System.out.println(">> ConcreteClass absM1");
	}

	@Override
	public void absM2() {
		System.out.println(">> ConcreteClass absM2");
	}
}