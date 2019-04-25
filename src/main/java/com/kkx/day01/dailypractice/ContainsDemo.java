package com.kkx.day01.dailypractice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContainsDemo {
	public static void main(String[] args) {
		List<Foo> list = new ArrayList<>();
		list.add(new Foo(1));
		list.add(new Foo(2));
		Foo b = new Foo(2);
		//	Foo a =b;
		//	System.out.println(a==b);
		System.out.println(list.contains(b));
		Set<Foo> set = new HashSet<>();
		set.add(new Foo(3));
		set.add(new Foo(4));
		Foo c = new Foo(4);
		System.out.println(set.contains(c));

	}
}

class Foo {
	private int a;

	public Foo(int a) {
		this.a = a;
	}

	@Override
	public int hashCode() {
		return a;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Foo foo = (Foo) o;

		return a == foo.a;
	}


}
