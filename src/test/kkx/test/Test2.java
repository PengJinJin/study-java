package kkx.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test2 {

	@Test(dependsOnMethods = {"a"})
	public void b() {
		System.out.println("Test2.b");
	}

	@Test
	public void a() {
		System.out.println("Test2.a");
	}

	@Test
	@Parameters({"yhm","pwd"})
	public void login(String uname, String pwd) {
		System.out.println(uname+";"+pwd);
	}

	@DataProvider(name = "LTest")
	public Object[][] test() {
		return new Object[][]{
				{"qq1","123"},
				{"qq2","456"},
				{"qq3","789"}
		};
	}
	@Test(dataProvider = "LTest")
	public void login1(String name,String p) {
		System.out.println(name+";"+p);
	}
}
