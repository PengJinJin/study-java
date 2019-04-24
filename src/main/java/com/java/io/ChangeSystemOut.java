package com.java.io;

import java.io.PrintWriter;

public class ChangeSystemOut {

	public static void main(String[] args) {
		PrintWriter writer = new PrintWriter(System.out, true);
		writer.println("Hello World!");
	}

}
