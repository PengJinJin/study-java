package com.java.enumerated;

enum SecurityCategory {

	STOCK(Security.Stock.class), BOND(Security.Bond.class);

	Security[] values;

	SecurityCategory(Class<? extends Security> aClass) {
		this.values = aClass.getEnumConstants();
	}

	interface Security {
		enum Stock implements Security {
			SHORT, LONG, MARGIN;
		}

		enum Bond implements Security {
			MUNICIPAL, JUNK
		}
	}

	public Security randomSelection() {
		return Enums.random(values);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			SecurityCategory category = Enums.random(SecurityCategory.class);
			System.out.println(category + ": " + category.randomSelection());
		}
	}

}
