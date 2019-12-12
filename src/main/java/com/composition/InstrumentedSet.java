package com.composition;

import java.util.Collection;
import java.util.Set;

public class InstrumentedSet<E> extends ForwardSet<E> {

	private int addCount = 0;


	public InstrumentedSet(Set<E> set) {
		super(set);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}

}
