package com.java.io.exercise;

import com.java.util.Constant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class E14_BufferPerformance {

	static final String OUT_FILE = Constant.IO_EXERCISE_PATH + "E14_BufferPerformance.out";
	static final String FILE = Constant.IO_EXERCISE_PATH + "E14_BufferPerformance.java";

	public static void main(String[] args) throws IOException {
		List<String> list = E07_FileIntoList.read(FILE);
		try (
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUT_FILE)))
		) {
			int lineCount = 1;
			long t1 = System.currentTimeMillis();
			for (String s : list) {
				for (int i = 0; i < 100000; i++) {
					out.println(lineCount + ": " + s);
				}
				lineCount++;
			}
			long t2 = System.currentTimeMillis();
			System.out.println("buffered: " + (t2 - t1));

			try (PrintWriter out1 = new PrintWriter(new FileWriter(OUT_FILE))) {
				lineCount = 1;
				t1 = System.currentTimeMillis();
				for (String s : list) {
					for (int i = 0; i < 100000; i++)
						out1.println(lineCount + ": " + s);
					lineCount++;
				}
				t2 = System.currentTimeMillis();
			}
			System.out.println("unbuffered: " + (t2 - t1));

		}
	}

}
