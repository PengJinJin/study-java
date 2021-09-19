package root.com.java.enumerated;


import root.com.java.util.Print;

import static root.com.java.util.Print.print;

enum Shrubbery {
	GROUND, CRAWLING, HANGING
}

public class EnumClass {

	public static void main(String[] args) {
		for (Shrubbery s : Shrubbery.values()) {
			Print.print(s + " ordinal: " + s.ordinal());
			Print.printnb(s.compareTo(Shrubbery.CRAWLING) + " ");
			Print.printnb(s.equals(Shrubbery.CRAWLING) + " ");
			Print.print(s == Shrubbery.CRAWLING);
			Print.print(s.getDeclaringClass());
			Print.print(s.name());
			Print.print("--------------------");
		}

		for (String s : "HANGING CRAWLING GROUND".split(" ")) {
			Shrubbery shrub = Enum.valueOf(Shrubbery.class, s);
			Print.print(shrub);
		}
	}

}
