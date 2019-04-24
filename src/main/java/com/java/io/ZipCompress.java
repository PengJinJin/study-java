package com.java.io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

import static com.java.util.Print.print;

public class ZipCompress {

	public static void main(String[] args) throws IOException {
		try (
				FileOutputStream f = new FileOutputStream("test.zip");
				CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
				ZipOutputStream zos = new ZipOutputStream(csum);
				BufferedOutputStream out = new BufferedOutputStream(zos)
		) {
			zos.setComment("A test of Java Zipping");
			for (String str : args) {
				print("Writing file: " + str);
				BufferedReader in = new BufferedReader(new FileReader(str));
				zos.putNextEntry(new ZipEntry(str));
				int c;
				while ((c = in.read()) != -1) {
					out.write(c);
				}
				in.close();
				out.flush();
			}
			System.out.println(System.getProperty("user.dir"));
			print("Checksum: " + csum.getChecksum().getValue());

			print("Read file...");
		}

		try (
				FileInputStream in = new FileInputStream("test.zip");
				CheckedInputStream csumi = new CheckedInputStream(in, new Adler32());
				ZipInputStream in2 = new ZipInputStream(csumi);
				BufferedInputStream bis = new BufferedInputStream(in2)
		) {
			ZipEntry ze;
			while ((ze = in2.getNextEntry()) != null) {
				print("Reading file " + ze);
				int x;
				while ((x = bis.read()) != -1) {
					System.out.write(x);
				}
			}
			if (args.length == 1) {
				print("Checksum: " + csumi.getChecksum().getValue());
			}
			bis.close();
			// Alternative way to open and read zip files
			ZipFile zf = new ZipFile("test.zip");
			Enumeration e = zf.entries();
			while (e.hasMoreElements()) {
				ZipEntry ze2 = (ZipEntry) e.nextElement();
				print("File: " + ze2);
			}
		}
	}

}
