package com.java.enumerated.exercise;


import java.util.Random;

enum CartoonCharacter {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
	private static Random rand = new Random(47);

	public static CartoonCharacter next() {
		return CartoonCharacter.values()[rand.nextInt(CartoonCharacter.values().length)];
	}
}

/**
 * {@link com.java.enumerated.EnumImplementation}
 * <p>
 * 这两种方式对比<br/>
 * 优点:无需enum实例就可以调用<br/>
 * 缺点:如果是实现Generator,将无法调用这个方法
 * </p>
 */
public class E02_EnumStaticImplementation {

	public static void printNext() {
		System.out.println(CartoonCharacter.next() + "");
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			printNext();
		}
	}


}
