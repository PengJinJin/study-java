package com.java.io;

import java.io.*;

import static com.java.util.Print.print;

/**
 * 实现Serializable接口(不是Externalizable), 如果需要在readObject或writeObject做一些事情
 * 可以自己写两个自定义方法: private readObject和private writeObject, 它们会在ObjectOutputStream.writeObject和ObjectInputStream.readObject时被调用
 * 还有一个技巧是: 可以在自己的writeObject内部调用defaultWriteObject()或readObject内部调用defaultReadObject()方法执行默认的readObject和writeObject
 */
public class SerialCtl implements Serializable {

	private String a;
	private transient String b;

	public SerialCtl(String a, String b) {
		this.a = "Not Transient: " + a;
		this.b = "Transient: " + b;
	}

	@Override
	public String toString() {
		return a + "\n" + b;
	}

	/**
	 * transient字段必须在程序中明确的保存和恢复
	 *
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(b);
	}

	/**
	 * transient字段必须在程序中明确的保存和恢复
	 *
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		b = (String) in.readObject();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerialCtl sc = new SerialCtl("TestA", "TestB");
		print("before: \n" + sc);

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 ObjectOutputStream out = new ObjectOutputStream(baos)
		) {
			out.writeObject(sc);
			out.flush();

			try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
				sc = (SerialCtl) in.readObject();
				print("After: \n" + sc);
			}
		}
	}

}
