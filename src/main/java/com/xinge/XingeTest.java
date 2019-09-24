/*
package com.xinge;


import java.util.ArrayList;
import java.util.List;

public class XingeTest {

	public static void main(String[] args) {
//		String token = "3369b70f888db01e889c549e384f99c038a789de176992863629504cfca2601f";
//		String adToken = "d95937230d8edbedae2720974e4d656cf611127c";
		XingePush push = new XingePush();
//		JSONArray ja = push.queryTokenTags(adToken);
//		System.out.println(ja);
//		XingeApp xinge = new XingeApp(2200335395L, "2475626e021c7ed664b7bdfaafe740e4");
//		JSONObject ret = xinge.queryTokenTags(token);
//		System.out.println(ret);
//		JSONArray array = push.queryTokenOfAccount("13683598994");
//		System.out.println(array);
		List<String> accounts = new ArrayList<>();
		accounts.add("15131087520");
		System.out.println(push.pushAccountForAndroid("标题", "内容啦啦啦啦啦🤓", accounts));
	}

}
*/
