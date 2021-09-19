package root.com.java.enumerated;

import root.com.java.util.Generator;
import root.com.java.enumerated.exercise.E02_EnumStaticImplementation;

import java.util.Random;

/**
 * 枚举不可以继承, 但可以实现
 */
enum CartoonCharacter implements Generator<CartoonCharacter> {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

	private Random random = new Random(47);

	@Override
	public CartoonCharacter next() {
		return values()[random.nextInt(values().length)];
	}
}

/**
 * {@link E02_EnumStaticImplementation}
 */
public class EnumImplementation {

	public static <T> void printNext(Generator<T> rg) {
		System.out.println(rg.next() + "");
	}

	public static void main(String[] args) {
		// 这里必须要有一个enum实例才能调用上面的方法
		CartoonCharacter character = CartoonCharacter.BOB;
		for (int i = 0; i < 10; i++) {
			printNext(character);
		}
	}

}
