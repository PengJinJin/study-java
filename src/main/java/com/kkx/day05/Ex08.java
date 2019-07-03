package com.kkx.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ex08 {
	static List<Character> excludes = Arrays.asList('£¬', '¡£', '¡¢', ' ', '£»', '¡°', '¡±', '¡¶', '¡·');

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						Ex08.class.getResourceAsStream("Éú»îµÄ¶÷´Í.txt")
				));
		String s;
		Map<Character, Integer> map = new HashMap<>();
		while ((s = reader.readLine()) != null) {
			countChar(map, s);
		}
		List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort(new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		for (Map.Entry<Character, Integer> map1 : list) {
			System.out.println(map1.getKey() + ": " + map1.getValue());
		}
	}

	static void countChar(Map<Character, Integer> map, String s) {
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (excludes.contains(c)) continue;
			Integer count = map.get(c);
			count = count == null ? 1 : count + 1;
			map.put(c, count);
		}

	}
}
