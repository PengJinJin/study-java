package com.kkx.day01.dailypractice;

import java.util.HashMap;
import java.util.Map;

public class CountString {

	public static void main(String[] args) {
		String str = "aabcccd";
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			Integer count;
			if ((count = map.get(c)) == null) {
				count = 0;
			}
			map.put(c, ++count);
		}
		System.out.println(map);
	}

}
