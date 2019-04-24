package com.java.test.apple;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.java.test.apple.service.BufferedReaderProcessor;

public class BufferedReaderDemo {

	public static void main(String[] args) throws IOException {
		test3();
		// test2();
	}
	
	private static void test3() {
		int portNumber = 1337;
		Runnable r = () -> System.out.println(portNumber);
//		portNumber = 3317;
	}

	private static void test2() {
		Function<BufferedReader, String> f = (BufferedReader b) -> {
			try {
				return b.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	private static void test1() throws FileNotFoundException, IOException {
		String oneLine = processFile((BufferedReader br) -> br.readLine());
		String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
		System.out.println(oneLine);
		System.out.println("oneLine===================");
		System.out.println(twoLine);
		System.out.println("twoLine===================");
	}

	public static String processFile(BufferedReaderProcessor p) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("D://abc.txt"))) {
			return p.process(br);
		}
	}

}
