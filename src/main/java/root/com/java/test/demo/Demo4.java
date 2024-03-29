package root.com.java.test.demo;

import java.util.ArrayList;
import java.util.List;

public class Demo4 {

	public static void main(String[] args) {
		t2();
	}

	static void t2() {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		C c = new C();
		D d = new D();

		System.out.println("1--" + a1.show(b));
		System.out.println("2--" + a1.show(c));
		System.out.println("3--" + a1.show(d));
		System.out.println("4--" + a2.show(b));
		System.out.println("5--" + a2.show(c));
		System.out.println("6--" + a2.show(d));
		System.out.println("7--" + b.show(b));
		System.out.println("8--" + b.show(c));
		System.out.println("9--" + b.show(d));
	}

	static void t1() {
		List<String> cities = new ArrayList<>();
		cities.add("Beijing");
		cities.add("Hubei");
		cities.add("Chongqing");
		cities.add("Shanghai");

		cities.forEach(c -> {
			System.out.println(c);
		});
		// 或者
		cities.forEach(System.out::println);
	}

}

class A {
	public String show(D obj) {
		return ("A and D");
	}

	public String show(A obj) {
		return ("A and A");
	}

}

class B extends A {
	public String show(B obj) {
		return ("B and B");
	}

	public String show(A obj) {
		return ("B and A");
	}
}

class C extends B {

}

class D extends B {

}
