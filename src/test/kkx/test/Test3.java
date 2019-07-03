package kkx.test;
import kkx.testutil.ExcelUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test3 {

	@DataProvider(name = "testLoginData")
	public Object[][] testLoginData() {
		String flieName="";
		String sheetName = "";
		Object[][] object= ExcelUtil.readExcel(flieName,sheetName);
		return object;

	}
	@Test(dataProvider ="testLoginData")
	public void login(String uname,String pwd) {
		System.out.println(uname+";"+pwd);
	}
}
