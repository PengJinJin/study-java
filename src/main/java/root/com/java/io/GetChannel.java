package root.com.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {

	private static final int BSIZE = 1024;

	public static void main(String[] args) throws IOException {
		try (FileChannel fc = new FileOutputStream("data.txt").getChannel()) {
			fc.write(ByteBuffer.wrap("Some text ".getBytes()));
		}

		try (FileChannel rw = new RandomAccessFile("data.txt", "rw").getChannel()) {
			rw.position(rw.size());// Move to end
			rw.write(ByteBuffer.wrap("Some more".getBytes()));
		}

		try (FileChannel fc = new FileInputStream("data.txt").getChannel()) {
			// 只读访问必须调用allocate()方法 且需要分配大小
			// 更高的速度需使用allocateDirect() 这种分配开支更大
			ByteBuffer buff = ByteBuffer.allocate(BSIZE);
			fc.read(buff);
			buff.flip();
			while (buff.hasRemaining()) {
				System.out.print((char) buff.get());
			}
		}
	}

}
