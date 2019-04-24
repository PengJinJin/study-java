package com.java.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 文件枷锁
 */
public class FileLocking {

	public static void main(String[] args) throws IOException, InterruptedException {

		try (
				FileOutputStream out = new FileOutputStream("file.txt");
				// tryLock\lock方法获取整个文件的FileLock
				FileLock lock = out.getChannel().tryLock()
		) {

			if (lock != null) {
				System.out.println("Locked File!");
				TimeUnit.MILLISECONDS.sleep(100);
				lock.release();
				System.out.println("Released Lock!");
			}
		}
	}

}
