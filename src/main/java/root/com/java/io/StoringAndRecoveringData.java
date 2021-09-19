package root.com.java.io;

import root.com.java.util.Constant;

import java.io.*;

public class StoringAndRecoveringData {


	public static void main(String[] args) throws IOException {
		try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(Constant.IO_PATH + "data.txt")));
             DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(Constant.IO_PATH + "data.txt")))) {
			out.writeDouble(3.14159);
			out.writeBytes("That was pi");
			out.writeDouble(1.41413);
			out.writeBytes("Square root of 2");

			System.out.println(in.readDouble());
			System.out.println(in.readUTF());
			System.out.println(in.readDouble());
			System.out.println(in.readUTF());
		}
	}

}
