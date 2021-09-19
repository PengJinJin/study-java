package root.com.kkx.day01.dailypractice;

import java.util.*;

public class Ex1 {
	public static void main(String[] args) {
		String string = new String("uytree");
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (map.containsKey(c)) {
				int count = map.get(c) + 1;
				map.put(c, count);
			} else
				map.put(c, 1);
		}

		//map.keySet();//返回set类型的集合，值为所有key
		map.entrySet();//返回set类型集合，值为entry
		//Set<Map.Entry<Character,Integer>> set=map.entrySet();
		//List<Map.Entry<Character,Integer>> list=new ArrayList<>(set);
		List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort(new MyComparator1());
		for (Map.Entry<Character, Integer> entry : list) {
			System.out.println(entry.getKey() + "_" + entry.getValue());
		}

//		System.out.println(map);
	}

}

class MyComparator1 implements Comparator<Map.Entry<Character, Integer>> {

	@Override
	public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
		return o2.getValue() - o1.getValue();
	}
}