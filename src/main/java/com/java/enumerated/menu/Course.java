package com.java.enumerated.menu;

import com.java.enumerated.Enums;

public enum Course {

	APPETIZER(Food.Appetizer.class),
	MAINCOURSE(Food.MainCourse.class),
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class);

	private Food[] values;

	Course(Class<? extends Food> clazz) {
		values = clazz.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}

}
