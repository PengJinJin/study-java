package com.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe魔法类，可以绕过虚拟机，直接操作底层内存<br/>
 * Unsafe不能直接new，因为Unsafe必须要包由BootstrapClassLoader加载，需要通过反射来获取值
 */
public class UnsafeInstance {

	public static Unsafe reflectGetUnsafe() {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			return (Unsafe) field.get(null);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
