package com.java.test2;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionTest {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		String[] arrayA = new String[]{"A", "B", "C", "D", "E", "F"};
		String[] arrayB = new String[]{"B", "D", "F", "G", "H", "K"};
		String[] arrayC = new String[]{"A", "B", "C", "D", "E", "F"};
		List<String> listA = Arrays.asList(arrayA);
		List<String> listB = Arrays.asList(arrayB);
		List<String> listC = Arrays.asList(arrayC);
		System.out.println(CollectionUtils.isEqualCollection(listA, listC));
//		List<String> a = (List<String>) CollectionUtils.disjunction(listA, listB);
//		a.forEach(System.out::print);
	}

}
