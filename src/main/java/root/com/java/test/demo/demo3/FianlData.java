package root.com.java.test.demo.demo3;

import java.util.Random;

import root.com.java.test.demo.dome1.Water;

public class FianlData {

	static Random random = new Random();
	private final int VALUE_1 = 9;
	private static final int VALUE_2 = 10;
	private final Water water = new Water();
	private Water water2 = new Water();
	private final int[] a = { 1, 2, 3, 4, 5 };
	private final int i4 = random.nextInt(20);
	private static final int i5 = random.nextInt(20);

	@Override
	public String toString() {
		return i4 + " " + i5;
	}

	private static Random rand = new Random();
	private final int a1 = rand.nextInt(10);
	private static final int a2 = rand.nextInt(10);

	public static void main(String[] args) {

		FianlData data = new FianlData();
		System.out.println(data.a1 + " " + data.a2);
		data = new FianlData();
		System.out.println(data.a1 + " " + data.a2);
	}

}
