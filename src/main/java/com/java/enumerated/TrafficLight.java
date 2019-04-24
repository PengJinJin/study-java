package com.java.enumerated;


/**
 * 在switch中使用
 */

enum Signal {
	GREEN, YELLOW, RED
}

public class TrafficLight {

	Signal color = Signal.RED;

	public void change() {
		switch (color) {
			case RED:
				color = Signal.GREEN;
				break;
			case YELLOW:
				color = Signal.RED;
				break;
			case GREEN:
				color = Signal.YELLOW;
				break;
		}
	}

	@Override
	public String toString() {
		return "The traffic Light is " + color;
	}

	public static void main(String[] args) {
		TrafficLight light = new TrafficLight();
		for (int i = 0; i < 7; i++) {
			System.out.println(light);
			light.change();
		}
	}
}
