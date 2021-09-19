package root.com.composition;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 转发类,被装饰类
 *
 * @param <E>
 */
public class ForwardSet<E> implements Set<E> {

	//引用现有类的实例，增加私有域
	private final Set<E> set;

	public ForwardSet(Set<E> set) {
		this.set = set;
	}

	/*
	 * =================== 转发方法=============================
	 */

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return set.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return set.iterator();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return set.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return set.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return set.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return set.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return set.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return set.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return set.removeAll(c);
	}

	@Override
	public void clear() {
		set.clear();
	}

	@Override
	public boolean equals(Object obj) {
		return set.equals(obj);
	}

	@Override
	public String toString() {
		return set.toString();
	}

	@Override
	public int hashCode() {
		return set.hashCode();
	}
}
