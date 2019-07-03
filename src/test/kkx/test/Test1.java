package kkx.test;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test1 {


	@Test(expectedExceptions = {ArithmeticException.class})
	public void a() {
		System.out.println(1 / 0);
	}

	@Test(enabled = false)
	public void b() {
		System.out.println("bbbbbbbb");
	}

	@Test(timeOut = 2000)
	public void c() {
		try {
			TimeUnit.MILLISECONDS.sleep(3000);
			System.out.println("Test1.c");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("Test1.c");
	}

	@Test(groups = {"all"})
	public void d() {
		System.out.println("Test1.d");
	}

}
