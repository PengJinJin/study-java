package com.java.test.stream.bean;

import java.util.Arrays;
import java.util.List;

import com.java.test.enums.Type;

public class Dish {

	/**
	 * ����
	 */
	private String name;
	/**
	 * �ǲ����ص�
	 */
	private boolean vegetarian;
	/**
	 * ��·��
	 */
	private int calories;
	/**
	 * ����
	 */
	private Type type;

	public Dish() {
	}

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name;
		/*
		return "Dish [name: " + this.name + ", vegetarian: " + this.vegetarian + ", calories: " + this.calories
				+ ", type: " + this.type + "]";
		*/
	}

	public static List<Dish> getList() {
		return Arrays.asList(
				new Dish("pork", false, 800, Type.MEAT),
				new Dish("chicken", false, 400, Type.MEAT),
				new Dish("beef", false, 700, Type.MEAT),
				new Dish("french fries", true, 530, Type.OTHER),
				new Dish("rice", true, 350, Type.OTHER),
				new Dish("season fruit", true, 120, Type.OTHER),
				new Dish("pizza", true, 550, Type.OTHER),
				new Dish("prawns", false, 300, Type.FISH),
				new Dish("salmon", false, 450, Type.FISH));
	}

}
