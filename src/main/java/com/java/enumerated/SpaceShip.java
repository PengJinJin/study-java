package com.java.enumerated;

import static com.java.util.Print.print;

/**
 * 覆盖enum的方法
 */
public enum SpaceShip {

	SCOUT, CARGO, TRANSPORT, CRUISER, BATTLESHIP, MOTHERSHIP;

	@Override
	public String toString() {
		String id = name();
		String lower = id.substring(1).toLowerCase();
		return id.charAt(0) + lower;
	}

	public static void main(String[] args) {
		for (SpaceShip s : SpaceShip.values()) {
			print(s);
		}
	}

}
