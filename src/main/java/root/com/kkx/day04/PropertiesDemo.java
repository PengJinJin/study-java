package root.com.kkx.day04;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
	public static void main(String[] args) throws IOException {
		Properties pro = new Properties();
		pro.setProperty("aaa", "1");
		System.out.println(pro.getProperty("aaa"));
		FileInputStream file = new FileInputStream("test01.properties");
		pro.load(file);
		System.out.println(pro.getProperty("username"));
	}
}
