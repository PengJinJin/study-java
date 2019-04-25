package com.kkx.day01.homework;

public class E05_Person {
	private String name;
	private int age;
	/**
	 * 男：m
	 * 女：f
	 */
	private char gender;
	private E05_Person partner;

	public static void main(String[] args) {
		E05_Person p1 = new E05_Person();
		p1.setAge(20);
		p1.setName("玫瑰");
		p1.setGender('f');
		E05_Person p2 = new E05_Person();
		p2.setGender('m');
		p2.setAge(22);
		p2.setName("玫瑰玫瑰");
		p1.marry(p2);
		p1.marry(p2);

	}

	public void marry(E05_Person p) {

		if (this.gender == p.getGender()) {
			System.out.println("同性别不能结婚");
			return;
		} else if ((p.getGender() == 'm' && p.getAge() < 22) || (getGender() == 'm' && getAge() < 22)) {
			System.out.println("男性年龄小于22不能结婚");
			return;
		} else if ((p.getGender() == 'f' && p.getAge() < 20) || (getGender() == 'f' && getAge() < 20)) {
			System.out.println("女性年龄小于20不能结婚");
			return;
		} else if (p.getPartner() != null || this.getPartner() != null) {
			System.out.println("已婚不能结婚");
			return;
		} else {
			p.setPartner(this);
			this.setPartner(p);
			System.out.println("恭喜" + getName() + " 和" + p.getName() + "结婚");
			return;
		}


	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public E05_Person getPartner() {
		return partner;
	}

	public void setPartner(E05_Person partner) {
		this.partner = partner;
	}
}
