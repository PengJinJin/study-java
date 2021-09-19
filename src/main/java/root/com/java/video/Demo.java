package root.com.java.video;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class Demo {


	public static void main(String[] args) {
//		String url = "http://localhost:8080/test2.zip";
		String url = "https://translate.google.cn/translate_tts?ie=UTF-8&q=impossibly%20big&tl=en&total=1&idx=0&textlen=14&tk=359672.256064&client=webapp&prev=input";
		for (int i = 1; i <= 50; i++) {
			System.out.println(i + "=======================" + download(url));
		}
	}

	public static int download(String source) {
		BigDecimal totalDownloadSize = BigDecimal.ZERO;
		try {
			URL url = new URL(source);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(10 * 1000);
			if (conn.getResponseCode() == 200) {
				InputStream inputStream = conn.getInputStream();
				int len;
				byte[] buf = new byte[1024];// ����һ��������,��СΪ1024 byte
				while (-1 != (len = inputStream.read(buf))) {
					totalDownloadSize = totalDownloadSize.add(BigDecimal.valueOf(len));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return totalDownloadSize.divide(BigDecimal.valueOf(1024), 2, BigDecimal.ROUND_HALF_UP).intValue();
	}


}
