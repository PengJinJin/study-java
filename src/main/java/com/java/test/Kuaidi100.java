package com.java.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Kuaidi100 {
	private static String ID = "96a60866c6ad40a8";

	public static void main(String[] args) {
		try {
			String str = new KuaidiVo("ems", "1149818553732", "2", "1", "desc").toString();
			// StringBuffer buffer = new
			// StringBuffer("http://api.kuaidi100.com/api?");
			// buffer.append("id=").append(ID).append("&com=").append("ems").append("&nu=").append("1149818553732")
			// .append("&valicode=&show=").append("2").append("&muti=").append("1").append("&order=desc");
//			URL url = new URL("https://www.kuaidi100.com/query?type=zhongtong&postid=762998617777&id=1&valicode=&temp=0.9825352108775021");
			URL url = new URL("https://www.kuaidi100.com/query?type=ems&postid=1149818553732&id=1&valicode=&temp=0.9825352108775021");
			System.out.println(str);
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null) {
				type = con.getContentType();
			}
			if (type == null || type.trim().length() == 0 || type.trim().indexOf("text/html") < 0) {
				return;
			}

			if (type.indexOf("charset=") > 0) {
				charSet = type.substring(type.indexOf("charset=") + 8);
			}

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					// String newContent = new String(b, 0, numRead);
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			System.out.println("content:" + content);
			urlStream.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
