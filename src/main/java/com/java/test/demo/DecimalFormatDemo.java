package com.java.test.demo;

import java.text.DecimalFormat;

public class DecimalFormatDemo {

	/**
	 * 格式化数字
	 * 
	 * @param pattern
	 *            格式
	 * @param value
	 *            值
	 */
	static void singleFormat(String pattern, double value) {
		DecimalFormat format = new DecimalFormat(pattern);
		String fmtStr = format.format(value);
		System.out.println(value + "格式化后是：" + fmtStr);
	}

	/**
	 * 使用applyPattern方法格式化
	 * 
	 * @param pattern
	 * @param value
	 */
	static void doubleFormat(String pattern, double value) {
		DecimalFormat format = new DecimalFormat();
		format.applyPattern(pattern);
		System.out.println(value + "格式化后是：" + format.format(value));
	}

	public static void main(String[] args) {
		// 都是四舍五入
		singleFormat("###,###.##", 123789.0194);
		singleFormat("###.##", 123789.0194);
		singleFormat("000.#kg", 66.66);

		doubleFormat("###.##", 123789.0194);
		doubleFormat("#.##%", 0.7896);
		doubleFormat("#.##\u2030", 0.7896);
		doubleFormat("#.##\u00A4", 0.7896);
	}

}
