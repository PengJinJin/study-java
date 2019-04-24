package com.java.util;

import java.util.Map;

public class MapEntry<K, V> implements Map.Entry<K, V> {
	private K k;
	private V v;

	public MapEntry(K k, V v) {
		this.k = k;
		this.v = v;
	}

	@Override
	public K getKey() {
		return k;
	}

	@Override
	public V getValue() {
		return v;
	}

	@Override
	public V setValue(V value) {
		V result = v;
		v = value;
		return result;
	}

	@Override
	public int hashCode() {
		return (k == null ? 0 : k.hashCode()) ^ (v == null ? 0 : v.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MapEntry)) {
			return false;
		}
		MapEntry entry = (MapEntry) obj;
		return (k == null ?
				entry.getKey() == null : k.equals(entry.getKey())) &&
				(v == null ?
						entry.getValue() == null : v.equals(entry.getValue()));
	}

	@Override
	public String toString() {
		return k + ": " + v;
	}
}
