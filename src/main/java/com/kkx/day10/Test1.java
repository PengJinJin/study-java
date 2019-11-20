package com.kkx.day10;

import org.apache.log4j.Logger;

public class Test1 {

	private static Logger logger = Logger.getLogger(Test1.class);

	public static void main(String[] args) {
		logger.warn("info");
		logger.error("error");
	}

}
