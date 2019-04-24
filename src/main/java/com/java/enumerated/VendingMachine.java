package com.java.enumerated;

import java.util.EnumMap;

import static com.java.enumerated.Input.*;

enum Category {

	MONEY(NICKEL, DIME, QUARTER, DOLLAR),
	ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
	QUIT_TRANSACTION(ABORT_TRANSACTION),
	SHUT_DOWN(STOP),
	;

	private Input[] values;

	Category(Input... values) {
		this.values = values;
	}

	private static EnumMap<Input, Category> categories = new EnumMap<>(Input.class);

	static {
		for (Category c : Category.class.getEnumConstants()) {
			for (Input i : c.values) {
				categories.put(i, c);
			}
		}
	}

	public static Category categorize(Input input) {
		return categories.get(input);
	}
}

public class VendingMachine {
}
