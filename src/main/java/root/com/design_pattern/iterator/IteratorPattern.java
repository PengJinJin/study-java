package root.com.design_pattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class IteratorPattern {
	public static void main(String[] args) {
		Aggregate ag = new ConcreteAggregate();
		ag.add("ZhongShan DX");
		ag.add("HuaNan LG");
		ag.add("ShaoGuan XY");
		System.out.println(">>>>>content:");

		Iterator iterator = ag.getIterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + "\t");
		}
		System.out.println();
		Object first = iterator.first();
		System.out.println("First is: " + first);
	}

}

// 抽象聚合
interface Aggregate {
	void add(Object o);

	void remove(Object o);

	Iterator getIterator();
}

// 具体聚合
class ConcreteAggregate implements Aggregate {

	private List<Object> list = new ArrayList<>();

	@Override
	public void add(Object o) {
		list.add(o);
	}

	@Override
	public void remove(Object o) {
		list.remove(o);
	}

	@Override
	public Iterator getIterator() {
		return new ConcreteIterator(list);
	}
}

// 抽象迭代器
interface Iterator {
	Object first();

	Object next();

	boolean hasNext();
}

// 具体迭代器
class ConcreteIterator implements Iterator {
	private List<Object> list = null;
	private int index = -1;

	public ConcreteIterator(List<Object> list) {
		this.list = list;
	}

	@Override
	public Object first() {
		return list.get(0);
	}

	@Override
	public Object next() {
		return this.hasNext() ? list.get(++index) : null;
	}

	@Override
	public boolean hasNext() {
		return index < list.size() - 1;
	}
}
