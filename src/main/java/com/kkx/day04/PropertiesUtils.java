package com.kkx.day04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	private Properties pro;
	private String fileName;

	/**
	 * 通过一个文件名，直接获取Properties
	 *
	 * @param fileName 工程下的.properties文件
	 */
	public PropertiesUtils(String fileName) {
		try {
			this.fileName = fileName;
			pro = new Properties();
			pro.load(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过一个Class对象和一个文件名，直接获取Properties
	 *
	 * @param cls      Class对象
	 * @param fileName 包下的.properties文件
	 */

	public PropertiesUtils(Class cls, String fileName) {
		try {
			pro = new Properties();
			pro.load(cls.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取包下的跟调用类的类名相同的.properties文件
	 */
	public PropertiesUtils() {
		try {
			pro = new Properties();
			String clsName = Thread.currentThread().getStackTrace()[2].getClassName();
			Class cls = Class.forName(clsName);
			String className = clsName.substring(clsName.lastIndexOf(".") + 1);
			pro.load(cls.getResourceAsStream(className + ".properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取属性
	 *
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return pro.getProperty(key);
	}

	/**
	 * 设置属性
	 *
	 * @param key
	 * @param value
	 */
	public void setProperty(String key, String value) {
		try {
			pro.setProperty(key, value);
			pro.store(new FileOutputStream(fileName), key);
		} catch (Exception e) {
			;
		}
	}

}
