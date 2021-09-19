package root.com.java.containers;

import root.com.java.util.Countries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

public class Enumerations {

	public static void main(String[] args) {
		Vector<String> v = new Vector<>(Countries.names(10));
		Enumeration<String> enumeration = v.elements();
		while (enumeration.hasMoreElements()) {
			System.out.println(enumeration.nextElement());
		}
		enumeration = Collections.enumeration(new ArrayList<>());
	}

}
