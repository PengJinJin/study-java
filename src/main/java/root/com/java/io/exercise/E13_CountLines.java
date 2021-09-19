package root.com.java.io.exercise;

import root.com.java.util.Constant;

import java.io.*;

/****************** Exercise 13 *****************
 * Modify BasicFileOutput.java so that it uses
 * LineNumberReader to keep track of the line
 * count. Note that it’s much easier to just keep
 * track programmatically.
 ***********************************************/
public class E13_CountLines {

	static String file = Constant.IO_EXERCISE_PATH + "E13_CountLines.out";
	static String path = Constant.IO_EXERCISE_PATH + "E13_CountLines.java";

	public static void main(String[] args) throws IOException {
		try (
				LineNumberReader in = new LineNumberReader(new FileReader(path));// 输入流
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))// 输出流
		) {
			String s;
			while ((s = in.readLine()) != null) {
				out.println(in.getLineNumber() + ": " + s);
			}
		}
		System.out.println(E07_FileIntoList.read(file));
	}

}
