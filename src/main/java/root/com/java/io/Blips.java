package root.com.java.io;

import root.com.java.util.Print;

import java.io.*;

import static root.com.java.util.Print.print;

public class Blips {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Print.print("Constructing objects:");
		Blip1 b1 = new Blip1();
		Blip2 b2 = new Blip2();

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Blips.out"))) {
			Print.print("Saving objects:");
			oos.writeObject(b1);
			oos.writeObject(b2);
		}

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"))) {
			Print.print("Recovering Blip1");
			b1 = (Blip1) in.readObject();
			// OPPS! Throws an exception，因为blip1的默认构造方法是public
			// 实现Externalizable接口，如果是恢复对象后，会调用所有的默认构造方法，而Serializable不会
			Print.print("Recovering Blip2");
//			b2 = (Blip2) in.readObject();
		}
	}

}

class Blip1 implements Externalizable {
	public Blip1() {
		Print.print("Constructor Blip1");
	}


	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		Print.print("Blip1.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		Print.print("Blip1.readExternal");
	}
}

class Blip2 implements Externalizable {
	Blip2() {
		Print.print("Constructor Blip2");
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		Print.print("Blip2.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		Print.print("Blip2.readExternal");
	}
}
