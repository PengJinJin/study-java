package com.java.containers;

import com.java.util.Countries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionMethods {

	public static void main(String[] args) {
		Collection<String> c = new ArrayList<>(Countries.names(6));
		c.add("ten");
		c.add("eleven");
		System.out.println(c);
		// make an array for the list
		Object[] objArray = c.toArray();

		List<String> ids;
		ids = Collections.nCopies(1, "zxhangsan");
		ids.forEach(System.out::println);
	}

}
