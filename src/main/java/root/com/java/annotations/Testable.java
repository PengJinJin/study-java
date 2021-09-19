package root.com.java.annotations;

import root.com.java.util.Test;

public class Testable {

	public void execute() {
		System.out.println("Executing...");
	}

	@Test
	void testExecute() {
		execute();
	}

}
