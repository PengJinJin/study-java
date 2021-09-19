package root.com.java.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryFile {

	public static byte[] read(File file) throws IOException {
		try (BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file))) {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		}
	}

	public static byte[] read(String bFile) throws IOException {
		return read(new File(bFile).getAbsoluteFile());
	}

}
