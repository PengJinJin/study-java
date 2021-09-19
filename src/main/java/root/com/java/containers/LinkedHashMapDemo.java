package root.com.java.containers;

import root.com.java.util.CountingMapData;

import java.util.LinkedHashMap;

import static root.com.java.util.Print.print;

/**
 * LinkedHashMap散列化所有的元素，但在遍历时，以元素的插入顺序返回结果
 * 可在构造器中设定LinkedHashMap，采用基于访问的 最近最少使用（LRU） 算法
 * 于是没有被使用过的（可以看作需要删除的）元素会放在前面
 */
public class LinkedHashMapDemo {

	public static void main(String[] args) {
		LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>(new CountingMapData(9));
		print(linkedMap);
		// Least-recently-used order:
		linkedMap = new LinkedHashMap<>(16, 0.75f, true);
		linkedMap.putAll(new CountingMapData(9));
		print(linkedMap);
		for (int i = 0; i < 6; i++) // Cause accesses:
			linkedMap.get(i);
		print(linkedMap);
		linkedMap.get(0);
		print(linkedMap);
	}

}
/* Output:
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{6=G0, 7=H0, 8=I0, 0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0}
{6=G0, 7=H0, 8=I0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 0=A0}
*///:~
