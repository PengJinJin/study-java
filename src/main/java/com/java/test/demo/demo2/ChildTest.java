package com.java.test.demo.demo2;

public class ChildTest extends FatherTest {

	protected void showTemperature() {
		System.out.println("my temperature is 100 centigrade");
	}
	
	public static void main(String[] args) {
		FatherTest f = new ChildTest();
		f.showTemperature();
	}
	
}
