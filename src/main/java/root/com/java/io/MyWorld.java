package root.com.java.io;

import root.com.java.util.Print;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static root.com.java.util.Print.print;

/**
 * 这个例子是为了<br/>
 * 1.如果两个对象都指向第三个对象的引用, 再对这两个对象进行序列化, 发生的事情<br/>
 * 2.如果恢复这两个对象, 第三个对象出现的次数<br/>
 * 3.如果将这两个对象序列化成磁盘上的文件, 然后对它们进行反序列化还原的结果<br/>
 * <p>
 * 这复制的是整个对象网 而不是引用
 */


class House implements Serializable {

}

class Animal implements Serializable {
	private String name;
	private House preferredHouse;

	public Animal(String name, House preferredHouse) {
		this.name = name;
		this.preferredHouse = preferredHouse;
	}

	@Override
	public String toString() {
		return name + "[" + super.toString() + "], " + preferredHouse + "\n";
	}
}

public class MyWorld {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		House house = new House();
		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal("Bosco the dog", house));
		animals.add(new Animal("RalPh the hamster", house));
		animals.add(new Animal("Molly the cat", house));
		Print.print("animals: " + animals);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);

		oos.writeObject(animals);
		oos.writeObject(animals);// 第二次写入
		// 写入到一个不同的stream
		ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		ObjectOutputStream oos2 = new ObjectOutputStream(baos2);
		oos2.writeObject(animals);
		// 取出来
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(baos2.toByteArray()));
		List animals1 = (List) ois.readObject(),
				animals2 = (List) ois.readObject(),
				animals3 = (List) ois2.readObject();
		Print.print("animals1: " + animals1);
		Print.print("animals2: " + animals2);
		Print.print("animals3: " + animals3);
	}

}
