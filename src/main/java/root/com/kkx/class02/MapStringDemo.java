package root.com.kkx.class02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MapStringDemo {

	public static void main(String[] args) throws IOException {
		// 现在需要做的是: 使用IO流读取一个小说,然后再进行下面的一步

		// 使用输入字符流来进行读取文件的操作
		InputStreamReader isr = new InputStreamReader(new FileInputStream("MapStringDemo.txt"), "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		StringBuilder builder = new StringBuilder();
		String str;

		while ((str = reader.readLine()) != null) {
			builder.append(str);
		}
		reader.close();
		isr.close();
		// 一个字符串，每个字符出现的次数，按出现次数顺序排列
//		String s = "woaizhuzhuhequanquan";
		String s = builder.toString();
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
