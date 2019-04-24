package com.java.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BufferedReader.readLine()每次读取一行
 */
public class BufferedInputFile {

	public static String read(String filename) throws IOException {
		try (
				InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filename), "GBK");
				BufferedReader in = new BufferedReader(inputStreamReader)
		) {
			String s;
			StringBuilder builder = new StringBuilder();
			while ((s = in.readLine()) != null) {
				builder.append(s + "\n");
			}
			return builder.toString();
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println(read("D://test1.txt"));
	}


}
