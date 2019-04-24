package com.java.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute {

	public static void command(String command) {
		boolean error = false;

		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while ((s = results.readLine()) != null) {
				System.out.println(s);
			}

			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((s = errors.readLine()) != null) {
				System.err.println(s);
				error = true;
			}

		} catch (Exception e) {
			if (!command.startsWith("CMD /C"))
				command("CMD /C" + command);
			else
				e.printStackTrace();
//				throw new RuntimeException(e);
		}
		if (error) {
			throw new OSExecuteException("Errors executing " + command);
		}

	}

}
