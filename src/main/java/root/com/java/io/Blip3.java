package root.com.java.io;

import root.com.java.util.Print;

import java.io.*;

import static root.com.java.util.Print.print;

public class Blip3 implements Externalizable {

	private int i;
	private String s;

	public Blip3() {
		Print.print("Blip3 Constructor");
		// s, i not initialized
	}

	public Blip3(int i, String s) {
		Print.print("Blip3(int i, String s)");
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
		Print.print("Blip3 writeExternal");
		out.writeObject(s);
		out.writeInt(i);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		Print.print("Blip3 readExternal");
//		s = (String) in.readObject();
//		i = in.readInt();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Print.print("Constructing objects:");
		Blip3 b = new Blip3(66, "A String ");
		Print.print(b);

		try (
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Blip3.out"))
		) {
			Print.print("Saving object:");
			oos.writeObject(b);
		}

		try (
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Blip3.out"))
		) {
			Print.print("Recovering object:");
			b = (Blip3) ois.readObject();
			Print.print(b);
		}
	}
}
