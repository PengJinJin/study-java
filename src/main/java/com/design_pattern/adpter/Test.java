package com.design_pattern.adpter;


import com.design_pattern.adpter.interfaces.InAdapter;

public class Test {

	// 类适配器模式测试
	static class ClassTest {
		public static void main(String[] args) {
			ClassAdapter adapter = new ClassAdapter();
			adapter.method1();
			adapter.method2();
		}
	}

	// 对象适配器测试
	static class ObjTest {
		public static void main(String[] args) {
			ObjAdapter oa = new ObjAdapter(new Adaptee());
			oa.method1();
			oa.method2();
		}
	}

	// 接口适配器测试
	static class InTest {
		public static void main(String[] args) {
			InAdapter ia = new InAdapter();
			ia.method1();
			ia.method2();
			ia.method3();
		}
	}

}

