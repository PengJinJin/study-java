package com.java.annotations;

import com.java.util.Test;

public class Testable {

	public void execute() {
		System.out.println("Executing...");
	}

	@Test
	void testExecute() {
		execute();
	}

}
