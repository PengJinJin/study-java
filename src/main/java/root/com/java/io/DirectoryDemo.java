package root.com.java.io;//: io/DirectoryDemo.java
// Sample use of Directory utilities.


import root.com.java.util.PPrint;
import root.com.java.util.Print;

import java.io.File;

import static root.com.java.util.Print.print;

public class DirectoryDemo {
	public static void main(String[] args) {
		// All directories:
		PPrint.pprint(Directory.walk(".").dirs);
		// All files beginning with 'T'
		for (File file : Directory.local(".", "T.*"))
			Print.print(file);
		Print.print("----------------------");
		// All Java files beginning with 'T':
		for (File file : Directory.walk(".", "T.*\\.java"))
			Print.print(file);
		Print.print("======================");
		// Class files containing "Z" or "z":
		for (File file : Directory.walk(".", ".*[Zz].*\\.class"))
			Print.print(file);
	}
} /* Output: (Sample)
[.\xfiles]
.\TestEOF.class
.\TestEOF.java
.\TransferTo.class
.\TransferTo.java
----------------------
.\TestEOF.java
.\TransferTo.java
.\xfiles\ThawAlien.java
======================
.\FreezeAlien.class
.\GZIPcompress.class
.\ZipCompress.class
*///:~
