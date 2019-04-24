package com.java.io;

import java.io.*;

import static com.java.util.Print.print;

public class Blip3 implements Externalizable {

	private int i;
	private String s;

	public Blip3() {
		print("Blip3 Constructor");
		// s, i not initialized
	}

	public Blip3(int i, String s) {
		print("Blip3(int i, String s)");
		this.i = i;
		this.s = s;
		// s和i仅在非默认构造函数中初始化
		// s & i initialized only in non-default constructor.
	}

	@Override
	public String toString() {
		return s + i;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		print("Blip3 writeExternal");
		out.writeObject(s);
		out.writeInt(i);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		print("Blip3 readExternal");
//		s = (String) in.readObject();
//		i = in.readInt();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		print("Constructing objects:");
		Blip3 b = new Blip3(66, "A String ");
		print(b);

		try (
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Blip3.out"))
		) {
			print("Saving object:");
			oos.writeObject(b);
		}

		try (
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Blip3.out"))
		) {
			print("Recovering object:");
			b = (Blip3) ois.readObject();
			print(b);
		}
	}
}
