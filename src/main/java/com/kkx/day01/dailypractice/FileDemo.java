package com.kkx.day01.dailypractice;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class FileDemo {
	public static void main(String[] args) throws IOException {
		File file = new File("src/day01");
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.exists());
		File file1 = new File("a.txt");
		if (!file1.exists()) {
			file1.createNewFile();
		}
		File[] files = file.listFiles();
		for (File f : files) {
			System.out.println(f.getAbsolutePath());
		}
		files = file.listFiles(f -> f.isFile() && f.getName().endsWith(".java"));

		files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isFile() && f.getName().endsWith(".java");
			}
		});
		System.out.println("========");
		for (File f : files) {
			System.out.println(f.getAbsolutePath());
		}

	}
}
