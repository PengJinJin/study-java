package root.com.java.enumerated;

import java.util.EnumMap;

enum Category {

	MONEY(Input.NICKEL, Input.DIME, Input.QUARTER, Input.DOLLAR),
	ITEM_SELECTION(Input.TOOTHPASTE, Input.CHIPS, Input.SODA, Input.SOAP),
	QUIT_TRANSACTION(Input.ABORT_TRANSACTION),
	SHUT_DOWN(Input.STOP),
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
