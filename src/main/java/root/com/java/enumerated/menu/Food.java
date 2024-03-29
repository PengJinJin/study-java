package root.com.java.enumerated.menu;


/**
 * 使用实现接口的方式 来给enum的分组，并且仍然Food类型
 */
public interface Food {

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
