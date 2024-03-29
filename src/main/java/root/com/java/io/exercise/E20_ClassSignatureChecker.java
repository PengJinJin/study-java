package root.com.java.io.exercise;

import root.com.java.io.BinaryFile;
import root.com.java.io.Directory;

import java.io.File;
import java.io.IOException;

public class E20_ClassSignatureChecker {
	final static byte[] signature =
			{(byte) 202, (byte) 254, (byte) 186, (byte) 190};

	public static void main(String[] args)
			throws IOException {
		String dir = ".";
		if (args.length == 1)
			dir = args[0];
		for (File file : Directory.walk(dir, ".*\\.class")) {
			byte[] bt = BinaryFile.read(file);
			for (int i = 0; i < signature.length; i++)
				if (bt[i] != signature[i]) {
					System.err.println(file + " is corrupt!");
					break;
				}
		}
	}
}
