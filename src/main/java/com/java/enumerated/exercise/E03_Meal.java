package com.java.enumerated.exercise;


import com.java.enumerated.Enums;

interface Food {
	enum Appetizer implements Food {
		SALAD, SOUP, SPRING_ROLLS;
	}

	enum Beverage implements Food {
		BEER, VINE, JUICE, COLA, WATER;
	}

	enum MainCourse implements Food {
		LASAGNE, BURRITO, PAD_THAI,
		LENTILS, HUMMOUS, VINDALOO;
	}

	enum Dessert implements Food {
		TIRAMISU, GELATO, BLACK_FOREST_CAKE,
		FRUIT, CREME_CARAMEL;
	}

	enum Coffee implements Food {
		BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
		LATTE, CAPPUCCINO, TEA, HERB_TEA;
	}
}

enum Course {
	APPETIZER(Food.Appetizer.class),
	BEVERAGE(Food.Beverage.class),
	MAINCOURSE(Food.MainCourse.class),
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class);

	Food[] values;

	Course(Class<? extends Food> aClass) {
		values = aClass.getEnumConstants();
	}

	public Food randomSelection() {
		return Enums.random(values);
	}

}

public class E03_Meal {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			for (Course course : Course.values()) {
				Food food = course.randomSelection();
				System.out.println(food);
			}
			System.out.println("---");
		}
	}

}
