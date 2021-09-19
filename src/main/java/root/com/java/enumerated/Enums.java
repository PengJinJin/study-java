package root.com.java.enumerated;

import java.util.Random;

/**
 * 这个类是为了消除重复，也就是消除麻烦
 */
public class Enums {

	private static Random rand = new Random(47);

	/**
	 * 随机取一个元素
	 *
	 * @param ec  将Class作为参数,可以利用Class对象得到enum实例数组
	 * @param <T> T是一个Enum实例
	 * @return
	 */
	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	/**
	 * 随机取一个元素
	 *
	 * @param values
	 * @param <T>
	 * @return
	 */
	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}

}
