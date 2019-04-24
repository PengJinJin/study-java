package com.java.test2;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class DecodeTest {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = URLDecoder.decode(
				"%5B%7B%22title%22%3A%22%E5%9B%9B%E7%A5%9E%E4%B8%B8%22%7D%2C%7B%22title%22%3A%22%E6%B4%81%E5%B0%94%E9%98%B4%22%7D%5D",
				"UTF-8");
		System.out.println(s);
	}
}
