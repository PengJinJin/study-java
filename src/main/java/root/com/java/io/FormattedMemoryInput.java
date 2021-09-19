package root.com.java.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * 格式化输出,DataInputStream是一个面向字节的I/O类
 */
public class FormattedMemoryInput {

	private static final String PATH = "D:\\eclipse-workspace\\MyWebSocket\\src\\com\\java\\io\\FormattedMemoryInput.java";

	public static void main(String[] args) throws IOException {
		try (
				DataInputStream stream = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read(PATH).getBytes()))
		) {
			while (true) {
				// 用readByte()方法 任何结果都是合法的结果 并不能检测到输入是否结束 所以会报错 所以需要另外的方法 见TestEOF
				System.out.print((char) stream.readByte());
			}
		} catch (EOFException e) {
			System.out.println("End of stream");
		}
	}

}
