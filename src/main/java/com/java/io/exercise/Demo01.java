package com.java.io.exercise;

import java.io.*;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.java.util.Print.print;

/**
 * 压缩某个目录下面的所有java文件 不包括子目录
 */
public class Demo01 {

	public static void main(String[] args) throws IOException {

		try (
				FileOutputStream fos = new FileOutputStream("demo01.zip");
				CheckedOutputStream cos = new CheckedOutputStream(fos, new Adler32());
				ZipOutputStream zos = new ZipOutputStream(cos);
				BufferedOutputStream out = new BufferedOutputStream(zos)
		) {
			File[] files = new File("D:\\eclipse-workspace\\MyWebSocket\\src\\com\\java\\io\\exercise").listFiles(
					new FilenameFilter() {
						private Pattern pattern = Pattern.compile(".java$");

						@Override
						public boolean accept(File dir, String name) {
							return pattern.matcher(name).find();
						}
					}
			);
			Objects.requireNonNull(files, "files must be not null.");
			for (File f : files) {
				print("Writing file: " + f.getAbsolutePath());
				BufferedReader in = new BufferedReader(new FileReader(f.getAbsolutePath()));
				zos.putNextEntry(new ZipEntry(f.getName()));
				int i;
				while ((i = in.read()) != -1) {
					out.write(i);
				}
				in.close();
				out.flush();
			}
			print("Checksum: " + cos.getChecksum().getValue());
		}

	}

}
