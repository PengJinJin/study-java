package root.com.java.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import root.com.java.test.vo.Apple;

public class Test4 {

	public static void main(String[] args) {
		List<Apple> apples = new ArrayList<>();
		apples.add(new Apple("white", 100));
		apples.add(new Apple("red", 120));
		apples.add(new Apple("black", 80));
		apples.add(new Apple("grep", 180));
		apples.add(new Apple("pink", 120));
		apples.add(new Apple("pink", null));

		Integer sum = apples.stream().filter(apple -> null != apple.getWeight()).mapToInt(apple -> apple.getWeight())
				.sum();
		System.out.println(sum);

		BigDecimal b1 = new BigDecimal(123);
		BigDecimal b2 = new BigDecimal(123.00);
		System.out.println(b2.equals(b1));
		String first = apples.stream().map(apple -> apple.getColor()).findFirst().orElse(null);
		System.out.println(first);
	}

}
