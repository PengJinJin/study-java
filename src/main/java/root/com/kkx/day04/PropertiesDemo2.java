package root.com.kkx.day04;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Properties;

public class PropertiesDemo2 {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Properties p = new Properties();
		Class c1 = PropertiesDemo2.class;
		PropertiesDemo2 p2 = new PropertiesDemo2();
		Class c2 = p2.getClass();
		Class c3 = Class.forName("d04.PropertiesDemo2");
		InputStream in = c1.getResourceAsStream("test.properties");
		InputStream in2 = c1.getClassLoader()
				.getResourceAsStream("test.properties");
		p.load(in);
		System.out.println(p.getProperty("username"));

		Class s = Aoo.class;
//		s.getMethods();
		for (Method m : s.getDeclaredMethods()) {
			System.out.println(m.getName() + ": " + m.getReturnType());
		}
		System.out.println("======");
		for (Field f : s.getDeclaredFields()) {
			System.out.println(f.getName() + ": " + f.getType().getName());
		}
	}
}

class Aoo {
	private BigDecimal a;
	public String b;

	private void f() {

	}

	public int g(int i) {
		return i;
	}
}
