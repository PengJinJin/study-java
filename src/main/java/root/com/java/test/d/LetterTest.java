package root.com.java.test.d;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LetterTest {

	public static void main(String[] args) {
		t1();
		t2();
	}

	public static void t1() {
		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
				.andThen(Letter::addFooter);

		List<String> ls = Arrays.asList("��labda��", "���labda����", "���labda����Zzz");
		List<String> list = ls.stream().map(transformationPipeline).collect(Collectors.toList());
		list.forEach(System.out::println);
	}

	public static void t2() {
		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline = addHeader.andThen(Letter::addFooter);

		List<String> ls = Arrays.asList("��labda��", "���labda����", "���labda����Zzz");
		List<String> list = ls.stream().map(transformationPipeline).collect(Collectors.toList());
		list.forEach(System.out::println);
	}

}
