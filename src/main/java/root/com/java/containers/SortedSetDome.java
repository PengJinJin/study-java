package root.com.java.containers;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static root.com.java.util.Print.print;

/**
 * SortedSet表示"按对象的比较函数对元素排序",而不是"插入顺序".插入顺序可以用LinkedHashSet
 */
public class SortedSetDome {

	public static void main(String[] args) {
		SortedSet<String> sortedSet = new TreeSet<>();
		Collections.addAll(sortedSet, "one two three four five six seven eight".split(" "));
		print(sortedSet); // [eight, five, four, one, seven, six, three, two]
		String low = sortedSet.first();
		String high = sortedSet.last();

		print(low);// eight
		print(high);// two

		Iterator<String> it = sortedSet.iterator();
		for (int i = 0; i <= 6; i++) {
			if (i == 3) {
				low = it.next();
			}
			if (i == 6) {
				high = it.next();
			} else {
				it.next();
			}
		}
		print(low);// one
		print(high);// two
		// 从low(包括)开始, 到high(不包括)结束
		print(sortedSet.subSet(low, high));// [one, seven, six, three]
		// high(不包括)的头
		print(sortedSet.headSet(high));// [eight, five, four, one, seven, six, three]
		// low(包括)的尾巴
		print(sortedSet.tailSet(low));// [one, seven, six, three, two]
	}
}
