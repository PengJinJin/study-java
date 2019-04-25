package com.kkx.day01.dailypractice;

import java.util.HashMap;
import java.util.Map;

public class T3 {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("pj", 18);
		Integer oldValue = map.put("pj", 17);
		System.out.println(oldValue);
		map.put("zhuzhu", 18);

		Integer zhuzhu = map.get("zhuzhu");
		System.out.println("zz" + zhuzhu);
		map.remove("pj");
		map.clear();
		map = new HashMap<>();
//		map = null;
		map.put("qq", 2);

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("k = " + entry.getKey());
			System.out.println("v = " + entry.getValue());
		}

		map.forEach((k, v) -> {
			System.out.println("k = " + k);
			System.out.println("v = " + v);
		});
	}

}
