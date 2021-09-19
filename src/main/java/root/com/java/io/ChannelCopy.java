package root.com.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("arguments: sourcefile destfile");
			System.exit(1);
		}

		try (
				FileChannel in = new FileInputStream(args[0]).getChannel();
				FileChannel out = new FileOutputStream(args[1]).getChannel()
		) {
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (in.read(buffer) != -1) {
				buffer.flip();// 准备写入
				out.write(buffer);
				buffer.clear();// 准备读取
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
