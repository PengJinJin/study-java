package com.java.io;

import java.io.*;

import static com.java.util.Print.print;

public class Blips {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		print("Constructing objects:");
		Blip1 b1 = new Blip1();
		Blip2 b2 = new Blip2();

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Blips.out"))) {
			print("Saving objects:");
			oos.writeObject(b1);
			oos.writeObject(b2);
		}

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"))) {
			print("Recovering Blip1");
			b1 = (Blip1) in.readObject();
			// OPPS! Throws an exception，因为blip1的默认构造方法是public
			// 实现Externalizable接口，如果是恢复对象后，会调用所有的默认构造方法，而Serializable不会
			print("Recovering Blip2");
//			b2 = (Blip2) in.readObject();
		}
	}

}

class Blip1 implements Externalizable {
	public Blip1() {
		print("Constructor Blip1");
	}


	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		print("Blip1.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		print("Blip1.readExternal");
	}
}

class Blip2 implements Externalizable {
	Blip2() {
		print("Constructor Blip2");
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		print("Blip2.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		print("Blip2.readExternal");
	}
}
