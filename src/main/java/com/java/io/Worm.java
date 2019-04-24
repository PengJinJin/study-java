package com.java.io;

import java.io.*;
import java.util.Random;

import static com.java.util.Print.print;

class Data implements Serializable {

	private int n;

	public Data(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return Integer.toString(n);
	}
}


public class Worm implements Serializable {

	private static Random rand = new Random(47);

	private Data[] d = {
			new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10)),
			new Data(rand.nextInt(10))
	};
	private Worm next;
	private char c;

	// Value of i == number of segments
	public Worm(int i, char x) {
		print("Worm constructor: " + i);
		c = x;
		if (--i > 0) {
			next = new Worm(i, (char) (x + 1));
		}
	}

	public Worm() {
		print("Default constructor");
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for (Data a : d) {
			result.append(a);
		}
		result.append(")");
		if (next != null) {
			result.append(next);
		}
		return result.toString();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Worm w = new Worm(6, 'a');
		print("w = " + w);
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"))) {
			out.writeObject("Worm storage\n");
			out.writeObject(w);
		}
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"))) {
			String s = (String) in.readObject();
			Worm w1 = (Worm) in.readObject();
			print(s + "w1 = " + w1);
		}
		try (
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)
		) {
			oos.writeObject("Worm storage\n");
			oos.writeObject(w);
			oos.flush();

			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			String s = (String) in.readObject();
			Worm w2 = (Worm) in.readObject();
			print(s + "w2 = " + w2);
		}
	}
}
