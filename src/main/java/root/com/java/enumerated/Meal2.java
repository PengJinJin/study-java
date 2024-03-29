package root.com.java.enumerated;

public enum Meal2 {

	APPETIZER(Food.Appetizer.class),
	MAINCOURSE(Food.MainCourse.class),
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class);

	Food[] values;

	Meal2(Class<? extends Food> aClass) {
		values = aClass.getEnumConstants();
	}

	interface Food {

		enum Appetizer implements Food {
			SALAD, SOUP, SPRING_ROLLS
		}

		enum MainCourse implements Food {
			LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO
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

	public Food randomSelection() {
		return Enums.random(values);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			for (Meal2 meal2 : Meal2.values()) {
				Food food = meal2.randomSelection();
				System.out.println(food);
			}
			System.out.println("---");
		}
	}


}
