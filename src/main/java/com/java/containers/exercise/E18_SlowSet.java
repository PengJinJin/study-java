package com.java.containers.exercise;

import com.java.util.Countries;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class E18_SlowSet {

	public static void main(String[] args) {
		SlowSet<String> set = new SlowSet<>();
		set.addAll(Countries.names(10));
		set.addAll(Countries.names(10));
		set.addAll(Countries.names(10));
		System.out.println(set);
	}

}

class SlowSet<K> extends AbstractSet<K> {
	private List<K> keys = new ArrayList<>();

	@Override
	public boolean add(K k) {
		if (!contains(k)) {
			keys.add(k);
			return true;
		}
		return false;
	}

	@Override
	public Iterator<K> iterator() {
		return keys.iterator();
	}

	@Override
	public int size() {
		return keys.size();
	}
}
