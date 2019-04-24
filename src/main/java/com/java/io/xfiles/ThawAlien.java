package com.java.io.xfiles;

import java.io.*;

public class ThawAlien {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("X.file"))) {
			Object mystery = in.readObject();
			System.out.println(mystery.getClass());
		}
	}

}
