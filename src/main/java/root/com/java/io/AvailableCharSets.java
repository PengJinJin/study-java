package root.com.java.io;

import root.com.java.util.Print;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

import static root.com.java.util.Print.print;

public class AvailableCharSets {

	public static void main(String[] args) {
		SortedMap<String, Charset> charSets = Charset.availableCharsets();

		Iterator<String> it = charSets.keySet().iterator();

		while (it.hasNext()) {
			String csName = it.next();
			Print.printnb(csName);

			Iterator aliases = charSets.get(csName).aliases().iterator();

			if (aliases.hasNext()) {
				Print.printnb(": ");
			}

			while (aliases.hasNext()) {
				Print.printnb(aliases.next());
				if (aliases.hasNext()) {
					Print.printnb(", ");
				}
			}
			Print.print();
		}
	}

}
