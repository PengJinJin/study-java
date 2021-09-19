package root.com.java.io;

import java.io.*;

public class TestEOF {

	private static final String PATH = "D:\\eclipse-workspace\\MyWebSocket\\src\\com\\java\\io\\TestEOF.java";

	public static void main(String[] args) throws IOException {
		try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(PATH)))) {
			// available表示"在没有阻塞情况下所能读取的字节数",对于文件是整个文件,但对于不同类型的流可能并非如此,需要谨慎使用
			while (in.available() != 0) {
				System.out.print((char) in.readByte());
			}
		}
	}

}
