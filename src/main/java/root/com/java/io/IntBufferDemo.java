package root.com.java.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {

	private static final int BSIZE = 1024;

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = bb.asIntBuffer();
		// Sort an array of int:
		ib.put(new int[]{11, 42, 47, 99, 143, 881, 1016});
		System.out.println(ib.get(3));// 99

		ib.put(3, 1811);// 替换
		ib.flip();

		while (ib.hasRemaining()) {
			int i = ib.get();
			System.out.println(i);
		}
	}

}
