package root.com.java.io;

import java.io.*;

public class BasicFileOutput {

	static String file = "BasicFileOutput.out";
	static final String PATH = "D:\\eclipse-workspace\\MyWebSocket\\src\\com\\java\\io\\BasicFileOutput.java";


	public static void main(String[] args) throws IOException {
		try (BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read(PATH)));
			 PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
			int lineCount = 1;
			String s;
			while ((s = in.readLine()) != null) {
				writer.println(lineCount++ + ": " + s);
			}
			System.out.println(BufferedInputFile.read(file));
		}
	}

}
