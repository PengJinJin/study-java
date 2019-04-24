package com.java.containers;

import com.java.util.Countries;
import com.java.util.MapEntry;

import java.util.*;

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

	static final int SIZE = 997;

	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

	/**
	 * put方法,返回新值
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public V put(K key, V value) {
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if (buckets[index] == null) {
			buckets[index] = new LinkedList<>();
		}
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		MapEntry<K, V> pair = new MapEntry<>(key, value);
		boolean found = false;
		ListIterator<MapEntry<K, V>> it = bucket.listIterator();
		while (it.hasNext()) {
			MapEntry<K, V> iPair = it.next();
			if (iPair.getKey().equals(key)) {
				oldValue = iPair.getValue();
				it.set(pair);// 替换旧的
				found = true;
				break;
			}
		}
		if (!found) {
			buckets[index].add(pair);
		}
		return oldValue;
	}

	/**
	 * 根据K获取V
	 *
	 * @param key
	 * @return
	 */
	@Override
	public V get(Object key) {
		int index = Math.abs(key.hashCode()) % SIZE;
		if (buckets[index] == null) {
			return null;
		}
		for (MapEntry<K, V> iPair : buckets[index]) {
			if (iPair.equals(key)) {
				return iPair.getValue();
			}
		}
		return null;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> set = new HashSet<>();
		for (LinkedList<MapEntry<K, V>> bucket : buckets) {
			if (bucket == null) {
				continue;
			}
			set.addAll(bucket);
			/*
			for (MapEntry<K, V> mpair: bucket){
				set.add(mpair);
			}
			*/
		}
		return set;
	}

	public static void main(String[] args) {
		SimpleHashMap<String, String> map = new SimpleHashMap<>();
		map.put("a", "a");
		map.put("b", "b");
		map.put("a", "c");
		map.putAll(Countries.capitals(15));
		System.out.println(map);
		System.out.println(map.get("EGYPT"));
		System.out.println(map.entrySet());
	}
}
