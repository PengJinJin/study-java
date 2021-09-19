package root.com.java.enumerated;

import root.com.java.util.Print;

import static root.com.java.enumerated.Spiciness.*;
import static root.com.java.util.Print.print;

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
		Print.print(new Burrito(NOT));
		Print.print(new Burrito(MEDIUM));
		Print.print(new Burrito(HOT));
	}

}
