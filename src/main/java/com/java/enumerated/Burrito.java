package com.java.enumerated;

import static com.java.enumerated.Spiciness.*;
import static com.java.util.Print.print;

public class Burrito {

	Spiciness degree;

	public Burrito(Spiciness degree) {
		this.degree = degree;
	}

	@Override
	public String toString() {
		return "Burrito is " + degree;
	}

	public static void main(String[] args) {
		print(new Burrito(NOT));
		print(new Burrito(MEDIUM));
		print(new Burrito(HOT));
	}

}
