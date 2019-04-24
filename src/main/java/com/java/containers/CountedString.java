package com.java.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java.util.Print.print;

public class CountedString {

	private static List<String> created = new ArrayList<>();

	private String s;
	private int id = 0;

	public CountedString(String s) {
		this.s = s;
		created.add(s);
		// id is the total number of instances of this string in use by CountedString
		// id是CountedString使用的此字符串的实例总数
		for (String str2 : created) {
			if (str2.equals(s)) {
				id++;
			}
		}
	}

	@Override
	public String toString() {
		return "String: " + s + " id: " + id + " hashCode(): " + hashCode();
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + s.hashCode();
		result = 37 * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CountedString && s.equals(((CountedString) obj).s) && id == ((CountedString) obj).id;
	}

	public static void main(String[] args) {
		Map<CountedString, Integer> map = new HashMap<>();
		CountedString[] cs = new CountedString[5];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = new CountedString("hi");
			map.put(cs[i], i);// autobox int -> Integer
		}
		print(map);
		for (CountedString cstring : cs) {
			print("Looking up " + cstring);
			print(map.get(cstring));
		}
	}
}
