package kkx.test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/*
testng参数化
 */
public class Test4 {

	@DataProvider(name="testData")
	public Object[][] testData(Method method) {

		if (method.getName().endsWith(("login"))) {
			return new Object[][]{
					{"zzz","111"},{"sss","222"}
			};
		}
		else {
			return new Object[][]{
					{1,2},{3,4}
			};
		}
	}
	@Test(dataProvider = "testData")
	public void login(String name,String pwd) {
		System.out.println(name+";"+pwd);
	}
    @Test(dataProvider = "testData")
	public void a(int a,int b) {
		System.out.println(a+";"+b);
	}
}
