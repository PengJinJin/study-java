package root.com.java.io;

import root.com.java.util.Constant;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {

	/**
	 * read a file as a single string
	 *
	 * @param fileName
	 * @return
	 */
	public static String read(String fileName) {
		StringBuilder builder = new StringBuilder();
		try (
//				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
				BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()))
		) {
			String s;
			while ((s = in.readLine()) != null) {
				builder.append(s).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return builder.toString();
	}

	/**
	 * Write a single file in one method call.
	 *
	 * @param fileName
	 * @param text
	 */
	public static void write(String fileName, String text) {
		try (
//				PrintWriter writer = new PrintWriter(new FileWriter(fileName));
				PrintWriter writer = new PrintWriter(new File(fileName).getAbsoluteFile())
		) {
			writer.print(text);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * Read a file, split by any regular expression
	 *
	 * @param fileName
	 * @param splitter
	 */
	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		// Regular expression split() often leaves an empty
		// String at the first position
		if (get(0).equals("")) {
			remove(0);
		}
	}

	public TextFile(String fileName) {
		this(fileName, "\n");
	}

	public void write(String fileName) {
		try (
				// PrintWriter out = new PrintWriter(new FileWriter(fileName));
				PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile())
		) {
			for (String item : this) {
				out.print(item);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
//		String path = Constant.IO_EXERCISE_PATH + "E13_CountLines.java";
//		System.out.println(read(path));
//
//		write("QwerBoom.txt", "System.out.println(read(path));");
		String filename = Constant.IO_PATH + "TextFile.java";
		String file = read(filename);
		write("test.txt", file);
		TextFile textFile = new TextFile("test.txt");
		textFile.write("test2.txt");

		TreeSet<String> words = new TreeSet<>(new TextFile(filename, "\\W+"));
		System.out.println(words.headSet("a"));
	}

}
