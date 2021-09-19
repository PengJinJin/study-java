package root.com.java.containers;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

class SetType {
	int i;

	public SetType(int i) {
		this.i = i;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof SetType && (i == ((SetType) obj).i);
	}

	@Override
	public String toString() {
		return Integer.toString(i);
	}
}

class HashType extends SetType {

	public HashType(int i) {
		super(i);
	}

	@Override
	public int hashCode() {
		return i;
	}
}

class TreeType extends SetType implements Comparable<TreeType> {

	public TreeType(int i) {
		super(i);
	}

	/**
	 * 降序排列
	 *
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(TreeType o) {
		return o.i < i ? -1 : (o.i == i ? 0 : 1);
	}
}

public class TypesForSets {

	/**
	 * 填充一个指定类型的set对象
	 *
	 * @param set
	 * @param type
	 * @param <T>
	 * @return
	 */
	static <T> Set<T> fill(Set<T> set, Class<T> type) {
		try {
			for (int i = 0; i < 10; i++) {
				set.add(type.getConstructor(int.class).newInstance(i));
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return set;
	}

	static <T> void test(Set<T> set, Class<T> type) {
		fill(set, type);
		fill(set, type);// try to add duplicates
		fill(set, type);
		System.out.println(set);
	}

	public static void main(String[] args) {
		test(new HashSet<>(), HashType.class);
		test(new LinkedHashSet<>(), HashType.class);
		test(new TreeSet<>(), TreeType.class);

		test(new HashSet<>(), SetType.class);// 没有重写hashCode()方法
		test(new HashSet<>(), TreeType.class);// 没有重写hashCode()方法
		test(new LinkedHashSet<>(), SetType.class);// 没有重写hashCode()方法
		test(new LinkedHashSet<>(), TreeType.class);// 没有重写hashCode()方法

		test(new TreeSet<>(), SetType.class);// 抛异常, 因为没有实现Comparable
		test(new TreeSet<>(), HashType.class);// 同上
	}

}
