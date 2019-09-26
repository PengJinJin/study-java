package com.composition;

import java.util.Arrays;
import java.util.HashSet;

public class TestComposition {

	public static void main(String[] args) {
		InstrumentedSet<String> s = new InstrumentedSet<>(new HashSet<>());
		s.addAll(Arrays.asList("a", "b", "c"));
		System.out.println(s.getAddCount());
	}

}
