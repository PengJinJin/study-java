package com.java.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Test2 {

	public static void main(String[] args) {
		test2();
	}

	public static void testFile() {
		String filePath = "D:\\abc.txt";
		try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
			lines.map(line -> line.toLowerCase()).forEach(System.out::println);
			// Files.lines(Paths.get(filePath)).forEach(System.out::print);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void test2() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);
		list.add(2);
		list.add(4);
		list.add(6);
		list.add(8);
		list.add(10);
		list.sort(Integer::compare);
		list.forEach(System.out::println);

		Stream<Integer> is = list.stream().sorted(Comparator.comparing(Integer::intValue).reversed());
		is.forEach(System.out::print);// output: 10987654321
	}

}
