package com.java.io;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class MappedIO {

	private static int numOfInts = 4000000;
	private static int numOfUbuffInts = 200000;

	private abstract static class Tester {
		private String name;

		public Tester(String name) {
			this.name = name;
		}

		public void runTest() throws IOException {
			System.out.println(name + ": ");

			long start = System.nanoTime();
			test();
			double duration = System.nanoTime() - start;
			System.out.format("%.2fs\n", duration / 1.0e9);
		}

		public abstract void test() throws IOException;
	}

	private static Tester[] tests = {
			new Tester("Stream Write") {
				@Override
				public void test() throws IOException {
					try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))))) {
						for (int i = 0; i < numOfInts; i++) {
							dos.writeInt(i);
						}
					}
				}
			},
			new Tester("Mapped Write") {
				@Override
				public void test() throws IOException {
					try (FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel()) {
						IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
						for (int i = 0; i < numOfInts; i++) {
							ib.put(i);
						}
					}
				}
			},
			new Tester("Stream Read") {
				@Override
				public void test() throws IOException {
					try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")))) {
						for (int i = 0; i < numOfInts; i++) {
							in.readInt();
						}
					}
				}
			},
			new Tester("Mapped Read") {
				@Override
				public void test() throws IOException {
					try (FileChannel fc = new FileInputStream("temp.tmp").getChannel()) {
						IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
						while (ib.hasRemaining()) {
							ib.get();
						}
					}
				}
			},
			new Tester("Stream Read/Write") {
				@Override
				public void test() throws IOException {
					try (RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"), "rw")) {
						raf.write(1);
						for (int i = 0; i < numOfUbuffInts; i++) {
							raf.seek(raf.length() - 4);// 定位到 倒数第四个
							raf.writeInt(raf.readInt());// 写入读取到的
						}
					}
				}
			},
			new Tester("Mapped Read/Write") {
				@Override
				public void test() throws IOException {
					try (FileChannel fc = new RandomAccessFile(new File("temp.tmp"), "rw").getChannel()) {
						IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
						ib.put(0);
						for (int i = 1; i < numOfUbuffInts; i++) {
							ib.put(ib.get(i - 1));
						}
					}
				}
			}
	};

	public static void main(String[] args) throws IOException {
		for (Tester test : tests) {
			test.runTest();
		}
	}

}
