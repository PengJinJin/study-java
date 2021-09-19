package root.com.kkx.day04.test;


import root.com.kkx.day04.PropertiesUtils;
import root.com.kkx.day04.StreamDemo;

public class TestProp {
	public static void main(String[] args) {
		PropertiesUtils p = new PropertiesUtils("TestProp.properties");
		p.setProperty("quanquan", "圈圈");

		PropertiesUtils p2 = new PropertiesUtils();
		System.out.println(p2.getProperty("qwe"));
		PropertiesUtils p3 = new PropertiesUtils(StreamDemo.class, "test.properties");
		System.out.println(p3.getProperty("username"));
	}
}
