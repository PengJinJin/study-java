package com.java.util;

import java.util.LinkedHashMap;

public class MapData<K, V> extends LinkedHashMap<K, V> {


	/**
	 * 单个Generator
	 *
	 * @param gen
	 * @param quantity
	 */
	public MapData(Generator<Pair<K, V>> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			Pair<K, V> p = gen.next();
			put(p.key, p.value);
		}
	}

	/**
	 * 两个Generator
	 *
	 * @param genK
	 * @param genV
	 * @param quantity
	 */
	public MapData(Generator<K> genK, Generator<V> genV, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), genV.next());
		}
	}

	/**
	 * 一个GenK和一个普通value
	 *
	 * @param genK
	 * @param value
	 * @param quantity
	 */
	public MapData(Generator<K> genK, V value, int quantity) {
		for (int i = 0; i < quantity; i++) {
			put(genK.next(), value);
		}
	}

	/**
	 * 一个IterableK和GenV
	 *
	 * @param genK
	 * @param genV
	 */
	public MapData(Iterable<K> genK, Generator<V> genV) {
		genK.forEach(k -> put(k, genV.next()));
	}

	/**
	 * 一个IterableK和V
	 *
	 * @param genK
	 * @param value
	 */
	public MapData(Iterable<K> genK, V value) {
		genK.forEach(k -> put(k, value));
	}

	public static <K, V> MapData<K, V> map(Generator<Pair<K, V>> gen, int quantity) {
		return new MapData<>(gen, quantity);
	}

	public static <K, V> MapData<K, V> map(Generator<K> genK, Generator<V> genV, int quantity) {
		return new MapData<>(genK, genV, quantity);
	}

	public static <K, V> MapData<K, V> map(Generator<K> genK, V value, int quantity) {
		return new MapData<>(genK, value, quantity);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> genK, Generator<V> genV) {
		return new MapData<>(genK, genV);
	}

	public static <K, V> MapData<K, V> map(Iterable<K> genK, V value) {
		return new MapData<>(genK, value);
	}

}
