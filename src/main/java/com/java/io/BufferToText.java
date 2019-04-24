package com.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BufferToText {

	private static final int BSIZE = 1024;

	/**
	 * 演示字符输出
	 * 要么写入时编码,使得写入有意义. 要么将其从缓冲器输出时解码
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		FileChannel fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some thing".getBytes()));
		fc.close();

		fc = new FileInputStream("data2.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		// 字符输出
		System.out.println(buff.asCharBuffer());// 乱码

		// 使用系统默认的Charset进行编码
		buff.rewind();// 转回到数据开始部分
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoding using " + encoding + ": " + Charset.forName(encoding).decode(buff));

		// 或者 可以用打印的东西进行编码
		fc = new FileOutputStream("data2.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes(StandardCharsets.UTF_16BE)));
		fc.close();

		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());

		// Use a CharBuffer to write through:
		fc = new FileOutputStream("data2.txt").getChannel();
		buff = ByteBuffer.allocate(24);
		buff.asCharBuffer().put("Some text");
		fc.write(buff);
		fc.close();

		// Read and display
		fc = new FileInputStream("data2.txt").getChannel();
		buff.clear();
		fc.read(buff);
		buff.flip();
		System.out.println(buff.asCharBuffer());
	}

}
