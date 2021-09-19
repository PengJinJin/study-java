package root.com.java.enumerated;

/**
 * 此类示例了非枚举类调用getEnumConstants方法,使用时异常
 */
public class NonEnum {

	public static void main(String[] args) {
		Class<Integer> aClass = Integer.class;
		try {
			for (Object o : aClass.getEnumConstants()) {
				System.out.println(o);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
