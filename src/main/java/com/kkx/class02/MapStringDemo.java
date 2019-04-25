package com.kkx.class02;

import java.util.*;

public class MapStringDemo {

	public static void main(String[] args) {
		// 一个字符串，每个字符出现的次数，按出现次数顺序排列
		String s = "woaizhuzhuhequanquan";
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				int index = map.get(c);
				map.put(c, ++index);
			} else {
				map.put(c, 1);
			}
		}
		System.out.println("before========");
		System.out.println(map);
		System.out.println("after========");
		Set<Map.Entry<Character, Integer>> set = map.entrySet();
		List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		System.out.println(list);
	}

}
