package com.java.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static com.java.util.Print.print;
import static com.java.util.Print.printnb;

/**
 * 能读能写的大文件
 */
public class LargeMappedFiles {

	static int length = 0x8FFFFFF;// 128M

	public static void main(String[] args) throws IOException {
		MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel()
				// 必须指定映射文件的初始位置, 和映射区域长度
				.map(FileChannel.MapMode.READ_WRITE, 0, length);
		for (int i = 0; i < length; i++) {
			out.put((byte) 'x');
		}
		print("Finished writing");
		for (int i = length / 2; i < length / 2 + 6; i++) {
			printnb((char) out.get(i));
		}
	}

}
