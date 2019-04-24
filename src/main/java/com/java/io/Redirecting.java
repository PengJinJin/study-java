package com.java.io;

import com.java.util.Constant;

import java.io.*;

public class Redirecting {

	private static final String FILE_NAME = Constant.IO_PATH + "Redirecting.java";

	/**
	 * I/O重定向 操纵的是字节流
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		PrintStream console = System.out;

		try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(FILE_NAME));
			 PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")))
		) {
			System.setIn(inputStream);
			System.setOut(out);
			System.setErr(out);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				String s;
				while ((s = br.readLine()) != null) {
					System.out.println(s);
				}
				System.setOut(console);
			}
		}
	}

}
