package com.java.test.apple.service;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {

	/**
	 * 处理BufferedReader接口
	 * 
	 * @param b
	 * @return
	 * @throws IOException
	 */
	String process(BufferedReader b) throws IOException;

}
