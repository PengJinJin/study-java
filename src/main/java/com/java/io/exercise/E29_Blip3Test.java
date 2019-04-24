package com.java.io.exercise;

import com.java.io.Blip3;

import java.io.*;

import static com.java.util.Print.print;

public class E29_Blip3Test {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		print("Constructing objects:");
		Blip3B b3 = new Blip3B(47, "A String ");
		print(b3);
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E29_Blip3Test.out"))		) {
			print("Saving object:");
			oos.writeObject(b3);
		}

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("E29_Blip3Test.out"))) {
			print("Recovering object:");
			b3 = (Blip3B) in.readObject();
			// 因为会调用无参构造，所以会打印一句: Blip3 Constructor
			print(b3);
		}

	}

}

class Blip3B extends Blip3 {
	public Blip3B() {
	}

	public Blip3B(int i, String s) {
		super(i, s);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		print("Blip3B.writeExternal");
// You must do this:
// out.writeObject(s);
// out.writeInt(i);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		print("Blip3B.readExternal");
// You must do this:
// out.writeObject(s);
// out.writeInt(i);
	}
}
