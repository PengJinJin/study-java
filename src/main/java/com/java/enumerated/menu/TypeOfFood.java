package com.java.enumerated.menu;

import static com.java.enumerated.menu.Food.*;

public class TypeOfFood {

	public static void main(String[] args) {
		// enum实现了Food, 所以都可以转型成Food
		Food food = Appetizer.SALAD;
		food = MainCourse.HUMMOUS;
		food = Dessert.GELATO;
		food = Coffee.CAPPUCCINO;
	}

}
