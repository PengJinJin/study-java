package root.com.kkx.day04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamDemo {
	public static void main(String[] args) throws IOException {
		try (
				FileOutputStream fos = new FileOutputStream("b.txt");
				FileInputStream fis = new FileInputStream("b.txt")
		) {
			fos.write(1);
			fos.write(2);
			fos.write(4);
			fos.write(8);
			fos.write(6);
			fos.write(9);
			int b;
			while ((b = fis.read()) != -1) {
				System.out.println(b);
			}
//			int read = fis.read();
//			System.out.println(read);
		}
	}
}
