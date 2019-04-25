package com.kkx.day01.dailypractice;

public class AnimalTest {

	public static void main(String[] args) {
		Animal a = new Cat();
		a.bark();

		System.out.println(a instanceof Cat);

		Animal1 a1 = new Dog1();
		a1.bark();
		a1.b();

		Animal2 a2 = new Cat2();
		a2.bark();
	}

}
