package com.java.enumerated;


/**
 * 除了使用values方法，还可以这样获取<br/>
 * getEnumConstants是Class的方法,所以即便不是枚举类,也可以调用,只不过使用的时候会报错而已<br/>
 * 见 {@link NonEnum}
 */
enum Search {
	HITHER, YON
}

public class UpcastEnum {
	public static void main(String[] args) {
		Search[] vals = Search.values();
		Enum e = Search.HITHER;
		for (Enum en : e.getClass().getEnumConstants()) {
			System.out.println(en);
		}
	}
}
