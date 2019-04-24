package com.java.enumerated;

import java.text.DateFormat;
import java.util.Date;

/**
 * 常量相关的方法.
 * 定义时 需要为enum定义一个
 */
public enum ConstantSpecificMethod {

	DATE_TIME {
		String getInfo() {
			return DateFormat.getDateInstance().format(new Date());
		}
	},
	CLASSPATH {
		String getInfo() {
			return System.getenv("CLASSPATH");
		}
	},
	VERSION {
		String getInfo() {
			return System.getProperty("java.version");
		}
	};

	abstract String getInfo();

	public static void main(String[] args) {
		for (ConstantSpecificMethod method : ConstantSpecificMethod.values()) {
			System.out.println(method.getInfo());
		}
	}

}
