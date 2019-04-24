package com.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class DirList2 {

	/**
	 * 这个参数必须是final的
	 *
	 * @param regex
	 * @return
	 */
	public static FilenameFilter filter(final String regex) {
		return new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);

			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		};
	}

	public static void main(String[] args) {
		File path = new File(".");
		String[] list;
		if (args.length == 0) {
			list = path.list();
		} else {
			list = path.list(filter(args[0]));
		}
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		Arrays.stream(list).forEach(System.out::println);

		long random = (long) ((Math.random() * 100) + 1);
		System.out.println(random);

		List<String> list2 = Arrays.asList("java", "python", "C++", "php", "java");
		Map<Boolean, List<String>> result = list2.stream().collect(partitioningBy(s -> s.length() > 3));
		System.out.println(result);

		//收集后转换为不可变List
		ArrayList<String> collect = list2.stream().collect(collectingAndThen(toList(), ArrayList::new));
		collect.forEach(System.out::println);
	}

}
