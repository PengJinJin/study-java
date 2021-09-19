package root.com.java.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 对部分文件加锁
 * Mapped一般用于非常大的文件,这种巨大的文件需要部分加锁,以便其他进程可以访问文件中未被加锁的部分
 */
public class LockingMappedFiles {

	static final int LENGTH = 0x8FFFFFF; // 128 MB

	static FileChannel fc;

	public static void main(String[] args) throws IOException {
		fc = new RandomAccessFile("test.dat", "rw").getChannel();
		MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			out.put((byte) 'x');
		}

		new LockAndModify(out, 0, LENGTH / 3);
		new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);

	}

	private static class LockAndModify extends Thread {
		private ByteBuffer buff;
		private int start, end;

		public LockAndModify(ByteBuffer buff, int start, int end) {
			this.start = start;
			this.end = end;
			buff.limit(end);
			buff.position(start);
			this.buff = buff.slice();
			start();
		}

		@Override
		public void run() {
			try {
				// exclusive lock with no overlap
				FileLock fl = fc.lock(start, end, false);
				System.out.println("Locked: " + start + " to " + end);
				// Perform modification
				while (buff.position() < buff.limit() - 1) {
					buff.put((byte) (buff.get() + 1));
				}
				fl.release();
				System.out.println("Released: " + start + " to " + end);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
