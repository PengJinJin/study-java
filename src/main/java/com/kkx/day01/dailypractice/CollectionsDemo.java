package com.kkx.day01.dailypractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionsDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("1234");
		list.add("321");
		list.add("1");
		list.add("3");
		list.add("32");

		//Collections.sort(list,new MyComparator());
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
			}
		});
		System.out.println(list);
	}
}

class MyComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
	}
}