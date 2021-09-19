package root.com.java.containers;

import static root.com.java.util.Print.print;

public class AssociativeArray<K, V> {

	/**
	 * 存放key和value, 二维数组[0]是key,[1]是value
	 */
	private Object[][] pairs;
	private int index;

	public AssociativeArray(int length) {
		pairs = new Object[length][2];
	}

	public void put(K key, V value) {
		if (index >= pairs.length) {
			throw new IndexOutOfBoundsException("数组越界 ");
		}
		pairs[index++] = new Object[]{key, value};
	}

	@SuppressWarnings("unchecked")
	public V get(K key) {
		for (int i = 0; i < index; i++) {
			if (key.equals(pairs[i][0])) {
				return (V) pairs[i][1];
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < index; i++) {
			builder.append(pairs[i][0]).append(": ").append(pairs[i][1]);
			if (i < index - 1) {
				builder.append("\n");
			}
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		AssociativeArray<String, String> map = new AssociativeArray<>(5);
		map.put("sky", "blue");
		map.put("grass", "green");
		map.put("ocean", "dancing");
		map.put("tree", "tall");
		map.put("earth", "brown");
		try {
			map.put("sun", "warm");
		} catch (IndexOutOfBoundsException e) {
			print("too many objects!!");
//			e.printStackTrace();
		}
		print(map);
		print(map.get("earth"));
	}
}
