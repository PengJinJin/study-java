package root.com.java.io.exercise;

import root.com.java.util.Print;

import java.io.*;

import static root.com.java.util.Print.print;

class Blip1 implements Externalizable {
	public Blip1() {
		Print.print("Blip1 Constructor");
	}

	public void writeExternal(ObjectOutput out)
			throws IOException {
		Print.print("Blip1.writeExternal");
	}

	public void readExternal(ObjectInput in)
			throws IOException, ClassNotFoundException {
		Print.print("Blip1.readExternal");
	}
}

public class E28_BlipCheck implements Externalizable {
	// E28_BlipCheck() {
// print("BlipCheck Constructor");
	// }
	public void writeExternal(ObjectOutput out)
			throws IOException {
		Print.print("BlipCheck.writeExternal");
	}

	public void readExternal(ObjectInput in)
			throws IOException, ClassNotFoundException {
		Print.print("BlipCheck.readExternal");
	}

	public static void main(String[] args) throws Exception {
// To make it run with Ant.
		Blips.main(args);
	}
}

class Blips {
	// Throw exceptions to console:
	public static void main(String[] args)
			throws IOException, ClassNotFoundException {
		Print.print("Constructing objects:");
		Blip1 b1 = new Blip1();
		E28_BlipCheck b2 = new E28_BlipCheck();
		ObjectOutputStream o =
				new ObjectOutputStream(
						new FileOutputStream("Blips.out"));
		Print.print("Saving objects:");
		o.writeObject(b1);
		o.writeObject(b2);
		o.close();
// Now get them back:
		ObjectInputStream in =
				new ObjectInputStream(
						new FileInputStream("Blips.out"));
		Print.print("Recovering b1:");
		b1 = (Blip1) in.readObject();
// OOPS! Throws an exception:
		Print.print("Recovering b2:");
		b2 = (E28_BlipCheck) in.readObject();
	}
}
