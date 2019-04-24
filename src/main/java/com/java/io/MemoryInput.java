package com.java.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * BufferedReader.read()每次读取一个字符
 */
public class MemoryInput {

	private static final String FILE_PATH = "D:/eclipse-workspace/MyWebSocket/src/com/java/io/MemoryInput.java";

	/**
	 * 寂寞来袭
	 * 旧雨衣
	 * 到底放在哪里
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try (
				InputStreamReader reader = new InputStreamReader(new FileInputStream(FILE_PATH), StandardCharsets.UTF_8);
				BufferedReader in = new BufferedReader(reader)
//				StringReader in = new StringReader(BufferedInputFile.read(FILE_PATH));
		) {
			int c;
			while ((c = in.read()) != -1) {
				System.out.print((char) c);
			}
		}
	}

}
