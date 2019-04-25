package com.kkx.class02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.java.util.PPrint.pprint;

/**
 * 表示ReadTextDemo这个类就是一个ArrayList,可以直接对其iterator
 */
public class ReadTextDemo extends ArrayList<ReadTextDemo.Item> {

	public void add(Character c) {
		if (!isEmpty()) {
			List<Character> characters = stream().map(Item::getText).collect(Collectors.toList());
       		/*
			// 循环找出Item中所有的text
			List<Character> characters = new ArrayList<>();
			// 直接遍历自身
			for (Item i : this) {
				characters.add(i.getText());
			}
			*/
			for (int i = 0; i < size(); i++) {
				Item item = get(i);
				// 如果需要加入的已经存在了
				if (characters.contains(c)) {
					// 循环到需要新增的等于get(i)
					if (item.getText().equals(c)) {
						// 把count次数递增并重新赋值
						set(i, new Item(c, ++item.count));
						break;
					}
				} else {
					// 如果要加入的不存在,就赋值1
					add(new Item(c, 1));
					break;
				}
			}
		} else {
			// 初次插入
			add(new Item(c, 1));
		}
	}

	/**
	 * 文本与字数类,自身实现Comparable
	 */
	static class Item implements Comparable<Item> {

		/**
		 * 字符
		 */
		private Character text;
		/**
		 * 出现次数
		 */
		private Integer count;

		/**
		 * Constructor
		 */
		Item(char text, int count) {
			this.text = text;
			this.count = count;
		}

		/**
		 * 自定义排序
		 */
		@Override
		public int compareTo(Item o) {
			return count.compareTo(o.count);
		}

		@Override
		public String toString() {
			return text + ": " + count;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Item item = (Item) o;

			return text != null ? text.equals(item.text) : item.text == null;
		}

		@Override
		public int hashCode() {
			return text != null ? text.hashCode() : 0;
		}

		// getter && setter
		public Character getText() {
			return text;
		}

		public void setText(Character text) {
			this.text = text;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
	}

	public static void main(String[] args) {
		try (
				InputStreamReader isr = new InputStreamReader(new FileInputStream("abc.txt"), StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(isr)
		) {
			ReadTextDemo items = new ReadTextDemo();
			String s;
			while ((s = reader.readLine()) != null) {
				// 去空格(https://www.cnblogs.com/LiuChunfu/p/5661810.html)
				s = s.replaceAll("\\s*", "");
				if (s.length() > 0) {
					for (char c : s.toCharArray()) {
						items.add(c);
					}
				}
			}
			// 排序,已经重写过compareTo
			Collections.sort(items);
			pprint(items);
		} catch (FileNotFoundException e) {
			System.err.println("没有找到文件");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
