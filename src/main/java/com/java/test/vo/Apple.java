package com.java.test.vo;

import java.util.ArrayList;
import java.util.List;

public class Apple {

	private String color;// —’…´
	private Integer weight;// ÷ÿ¡ø

	public Apple(String color, Integer weight) {
		this.color = color;
		this.weight = weight;
	}

	public Apple(Integer weight) {
		this.weight = weight;
	}

	public Apple() {
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Apple [color: " + this.color + ", weight: " + this.weight + "]";
	}

	public static List<Apple> getAppleList() {
		List<Apple> list = new ArrayList<>();
		list.add(new Apple("Green", 150));
		list.add(new Apple("Green", 180));
		list.add(new Apple("Green", 120));
		list.add(new Apple("Green", 210));
		list.add(new Apple("Red", 160));
		list.add(new Apple("Red", 130));
		list.add(new Apple("Red", 170));
		list.add(new Apple("White", 170));
		list.add(new Apple("White", 150));
		list.add(new Apple("White", 110));
		return list;
	}

}
