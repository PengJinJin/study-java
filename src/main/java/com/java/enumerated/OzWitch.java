package com.java.enumerated;

import static com.java.util.Print.print;

public enum OzWitch {

	WEST("Miss Gulch, aka the Wicked Witch of the West"),
	NORTH("Glinda, the Good Witch of the North"),
	EAST("Wicked Witch of the East, wearer of the Ruby " +
			"Slippers, crushed by Dorothy's house"),
	SOUTH("Good by inference, but missing");

	private String description;

	OzWitch(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static void main(String[] args) {
		for (OzWitch o : OzWitch.values()) {
			print(o + ": " + o.getDescription());
		}
	}
}
