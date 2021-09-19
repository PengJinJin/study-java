package root.com.java.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

public class Test5 {
	public static void main(String[] args) {
		test4();
	}

	public static void test3() {
		// �쳣ԭ����asList���ɵ�List��һ���̶����ȵ�List,�����Բ���
		String[] strs = new String[] { "a", "b", "c", "d", "e" };
		List<String> a = Arrays.asList(strs);
		a.remove(2);
		a.stream().forEach(System.out::println);
		// List<String> a = Arrays.stream(strs).collect(Collectors.toList());
		// a.remove(2);
		// System.out.print(a.size() + "=========");
		// a.stream().forEach(System.out::print);
	}

	public static void test1() {
		String[] strs = new String[] { "110", "111", "222" };
		System.out.println(Arrays.toString(strs));
		List<String> a = new ArrayList<>();
		a.add("a");
		a.add("b");
		a.add("c");
		a.add("d");
		List<String> b = new ArrayList<>();
		b.add("d");
		b.add("b");
		b.add("c");
		b.add("a");
		boolean isEqu = CollectionUtils.isEqualCollection(a, b);
		System.out.println("===" + isEqu);
	}

	public static void test2() {
		String[] strs1 = new String[] { "110", "111", "222", "666", "888" };
		String[] strs = new String[] { "110", "111", "222" };
		for (String str : strs1) {
			boolean isEqu = ArrayUtils.contains(strs, str);
			System.out.println("===" + isEqu);
		}
	}

	// for lists
	public static <T, U> List<U> convertList(List<T> from, Function<T, U> func) {
		return from.stream().map(func).collect(Collectors.toList());
	}

	// for arrays
	public static <T, U> U[] convertArray(T[] from, Function<T, U> func, IntFunction<U[]> generator) {
		return Arrays.stream(from).map(func).toArray(generator);
	}

	public static void test4() {
		// for lists
		List<String> stringList = Arrays.asList("1", "2", "3");
		List<Integer> integerList = convertList(stringList, s -> Integer.parseInt(s));
		integerList.stream().forEach(i -> {
			System.out.println(i.getClass());
		});
		System.out.println("===========");
		// for arrays
		String[] stringArr = { "1", "2", "3" };
		Double[] doubleArr = convertArray(stringArr, Double::parseDouble, Double[]::new);
		Arrays.stream(doubleArr).forEach(System.out::println);
	}

}
