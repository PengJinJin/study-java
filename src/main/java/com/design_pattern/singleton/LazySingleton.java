package com.design_pattern.singleton;

/**
 * 懒汉式单例实现
 */
public class LazySingleton {

	private static volatile LazySingleton instance = null;
	private static final byte[] b = new byte[0];

	private LazySingleton() {
	}

	public static LazySingleton getInstance() {
		synchronized (b) {
			if (instance == null) {
				instance = new LazySingleton();
			}
		}
		return instance;
	}

}
