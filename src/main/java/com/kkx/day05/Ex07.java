package com.kkx.day05;

import java.io.File;
import java.io.FileFilter;

public class Ex07 {
	public static void main(String[] args) {
		File file = new File(".");
		listFile(file);
	}

	public static void listFile(File file) {
		showFile(file);
		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isDirectory();
			}
		});
		for (File f : files) {
			listFile(f);
		}
	}

	public static void showFile(File file) {
		if (file.isDirectory()) {
			System.out.println(file.getAbsolutePath());
			File[] files = file.listFiles(new FileFilter() {
				@Override
				public boolean accept(File f) {
					return f.isFile() && f.getName().endsWith("java");
				}
			});
			for (File f : files) {
				System.out.println("\t" + f.getName());
			}
		}
	}
}
