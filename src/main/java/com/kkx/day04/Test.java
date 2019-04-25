package com.kkx.day04;

public class Test {

	public static void main(String[] args) {
		PropertiesUtils p = new PropertiesUtils("test.properties");
		String un = p.getProperty("username");
		p.setProperty("birthday", "1970_01_01");
		System.out.println(un);
	}

}
