package root.com.java.io.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E21_UpperCaseEcho {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s = reader.readLine()) != null) {
			System.out.println(s.toUpperCase());
		}
	}

}
