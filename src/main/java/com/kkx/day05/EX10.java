package com.kkx.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EX10 {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr =
				new InputStreamReader(
						EX10.class.getResourceAsStream("Éú»îµÄ¶÷´Í.txt"));
		BufferedReader reader = new BufferedReader(isr);
		String s;
		Map<Character, Integer> map = new HashMap<>();
		while ((s = reader.readLine()) != null) {
			reader(s, map);
		}
		reader.close();
		isr.close();
//		System.out.println(map);
		List<Map.Entry<Character, Integer>> list =
				new ArrayList<>(map.entrySet());
		list.sort(new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
				return o1.getValue() - o2.getValue();
			}
		});
		System.out.println(list);
	}

	static void reader(String a, Map<Character, Integer> map) {
		List<Character> excludes = Arrays.asList(' ', ',', '¡£','£¬');
		for (int i = 0; i < a.length(); i++) {
			char b = a.charAt(i);
			if (excludes.contains(b)) {

				continue;
			}

			int count = 0;
			if (map.containsKey(b)) {
				count = map.get(b) + 1;
			} else
				count = 1;
			map.put(b, count);
		}
	}
}


