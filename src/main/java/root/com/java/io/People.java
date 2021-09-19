package root.com.java.io;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;
import nu.xom.ParsingException;
import root.com.java.util.Print;

import java.io.IOException;
import java.util.ArrayList;

import static root.com.java.util.Print.print;

public class People extends ArrayList<Person> {

	public People(String fileName) throws ParsingException, IOException {
		Document document = new Builder().build(fileName);
		Elements elements = document.getRootElement().getChildElements();

		for (int i = 0; i < elements.size(); i++) {
			add(new Person(elements.get(i)));
		}
	}

	public static void main(String[] args) throws ParsingException, IOException {
		People people = new People("People.xml");
		Print.print(people);
	}

}
