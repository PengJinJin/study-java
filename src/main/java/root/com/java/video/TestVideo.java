package root.com.java.video;

import com.google.gson.Gson;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestVideo {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static final String TEMP_DIR = "D:/Download/temp/";
	private static final int CONN_TIMEOUT = 30 * 60 * 1000;
	private static final int READ_TIMEOUT = 30 * 60 * 1000;
	private static final String ENCODING = "UTF-8";
	private static final String TOKEN = "token";

	public static void main(String[] args) throws Exception {
		String videoUrl = "http://192.168.10.200/uploads/20190318101745WUNV6ZBX/20190318101745WUNV6ZBX.m3u8";
		String apiUrl = "http://192.168.10.110/login";
		String params = "username=admin&password=1";
		String token = getToken(apiUrl, params);// 获取token
		download(videoUrl, token);// 把token传到下载视频里面
	}

	public static String getToken(String toUrl, String params) throws Exception {
		HashMap map = sendPostRequest(toUrl, params);
		return String.valueOf(map.get("token"));
	}

	public static HashMap sendPostRequest(String toUrl, String params) throws Exception {
		String response = conn(toUrl, "POST", CONN_TIMEOUT, READ_TIMEOUT, ENCODING, params);
		Gson gson = new Gson();
		return gson.fromJson(response, HashMap.class);
	}

	public static double download(String videoUrl, String token) throws IOException {
		long startDownloadTime = System.currentTimeMillis();
		long lastDownloadTime = startDownloadTime;
		BigDecimal totalDownloadSize = BigDecimal.ZERO;
		BigDecimal lastDownloadSize = BigDecimal.ZERO;
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String err = TEMP_DIR + "err.txt";
		String fail = TEMP_DIR + "fail.txt";
		File tfile = new File(TEMP_DIR + uuid);
		if (!tfile.exists()) {
			tfile.mkdirs();
		}

		M3U8 m3u8ByURL = getM3U8ByURL(videoUrl);
		if (m3u8ByURL == null) {
			writeFileContent(err, format.format(new Date()) + "读取URL分片信息失败.");
			return 0.0D;
		} else {
			m3u8ByURL.setStartDownloadTime(format.format(new Date()));
			String basePath = m3u8ByURL.getBasepath();
			String log = TEMP_DIR + uuid + File.separator + uuid + ".txt";

			for (M3U8.Ts m3U8Ts : m3u8ByURL.getTsList()) {// 遍历视频的切片
				InputStream inputStream = null;
				File file;
				if (m3U8Ts.getFile().contains("/")) {
					file = new File(TEMP_DIR + uuid + File.separator + m3U8Ts.getFile().substring(0, m3U8Ts.getFile().indexOf("/")));
					if (!file.exists()) {
						file.mkdir();
					}
				}

				try {
					file = new File(TEMP_DIR + uuid + File.separator + m3U8Ts.getFile());
					if (!file.exists()) {
						URL url = new URL(basePath + m3U8Ts.getFile());
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setConnectTimeout(CONN_TIMEOUT);
						conn.setReadTimeout(READ_TIMEOUT);
						conn.setRequestProperty(TOKEN, token);
						conn.setDoInput(true);
						conn.setDoOutput(true);
						if (conn.getResponseCode() == 200) {
							inputStream = conn.getInputStream();
							byte[] buf = new byte[1024];

							int len;
							while ((len = inputStream.read(buf)) != -1) {
								totalDownloadSize = totalDownloadSize.add(BigDecimal.valueOf((long) len));
								if (System.currentTimeMillis() - lastDownloadTime >= 1000L) {
									BigDecimal speed = totalDownloadSize.subtract(lastDownloadSize).divide(BigDecimal.valueOf(1024L), 2, RoundingMode.HALF_UP);
									String content = "download_speed: " + format.format(new Date()) + " " + speed + "(KB/s)";
									writeFileContent(log, content);
									lastDownloadTime = System.currentTimeMillis();
									lastDownloadSize = totalDownloadSize;
								}
							}
						} else {
							writeFileContent(fail, "fail_file: " + uuid + File.separator + m3U8Ts.getFile());
						}
					}
				} catch (Exception e) {
					writeFileContent(err, errorTrackSpace(e));
				} finally {
					try {
						if (inputStream != null) {
							inputStream.close();
						}
					} catch (IOException var31) {
						;
					}

				}
			}

			m3u8ByURL.setEndDownloadTime(format.format(new Date()));
			BigDecimal total = totalDownloadSize.divide(BigDecimal.valueOf(1024L), 2, RoundingMode.HALF_UP);
			m3u8ByURL.setDownLoadSize(total);
			BigDecimal speed = total.divide(BigDecimal.valueOf(System.currentTimeMillis() - startDownloadTime).divide(BigDecimal.valueOf(1000L)), 2, RoundingMode.HALF_UP);
			m3u8ByURL.setAvgSpeed(speed);
			writeFileContent(log, "File download completed!\n" + m3u8ByURL);
			mergeFiles(tfile.listFiles(), "test.ts");
			return total.doubleValue();
		}
	}

	public static M3U8 getM3U8ByURL(String m3u8URL) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(m3u8URL).openConnection();
			if (conn.getResponseCode() == 200) {
				String realUrl = conn.getURL().toString();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String basepath = realUrl.substring(0, realUrl.lastIndexOf("/") + 1);
				M3U8 ret = new M3U8();
				ret.setBasepath(basepath);

				String line;
				float seconds = 0F;
				int mIndex;
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("#")) {
						if (line.startsWith("#EXTINF:")) {
							line = line.substring(8);

							if ((mIndex = line.indexOf(",")) != -1) {
								line = line.substring(0, mIndex);
							}
							try {
								seconds = Float.parseFloat(line);
							} catch (Exception e) {
								seconds = 0F;
							}
						}
						continue;
					}
					if (line.endsWith("m3u8")) {
						return getM3U8ByURL(basepath + line);
					}
					if (line.contains("?")) {
						line = line.substring(0, line.indexOf("?"));
					}
					ret.addTs(new M3U8.Ts(line, seconds));
					seconds = 0;
				}
				reader.close();
				return ret;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean mergeFiles(File[] fpaths, String resultPath) {
		if (fpaths == null || fpaths.length < 1) {
			return false;
		}
		if (fpaths.length == 1) {
			return fpaths[0].renameTo(new File(resultPath));
		}
		for (File fpath : fpaths) {
			if (!fpath.exists() || !fpath.isFile()) {
				return false;
			}
		}
		File resultFile = new File(resultPath);

		try {
			FileOutputStream fs = new FileOutputStream(resultFile, true);
			FileChannel resultFileChannel = fs.getChannel();
			FileInputStream tfs;
			for (File fpath : fpaths) {
				tfs = new FileInputStream(fpath);
				FileChannel blk = tfs.getChannel();
				resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
				tfs.close();
				blk.close();
			}
			fs.close();
			resultFileChannel.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean writeFileContent(String filepath, String newstr) throws IOException {
		boolean bool = false;
		String filein = newstr + "\r\n";
		FileOutputStream fos = null;
		PrintWriter pw = null;

		try {
			File file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}

			fos = new FileOutputStream(file, true);
			pw = new PrintWriter(fos);
			pw.write(filein.toCharArray());
			pw.flush();
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Write log error");
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return bool;
	}

	private static String errorTrackSpace(Throwable e) {
		StringBuffer buffer = new StringBuffer(format.format(new Date()));
		if (e != null) {
			buffer.append("\n").append(e.toString());
			for (StackTraceElement element : e.getStackTrace()) {
				buffer.append("\r\n\t").append(element.toString());
			}
		}
		return buffer.length() == 0 ? null : buffer.append("\n").toString();
	}

	/**
	 * 发送参数接收响应的post请求
	 *
	 * @param toUrl      请求地址
	 * @param parameters 参数（把map转化为parm1=xxx&parm2=xxx 发送请求）
	 * @return
	 */
	String conn(String toUrl, Map<String, String> parameters) throws Exception {
		return conn(toUrl, "POST", CONN_TIMEOUT, READ_TIMEOUT, ENCODING, generatorParamString(parameters));
	}

	/**
	 * 获取toUrl的返回值
	 *
	 * @param toUrl       接口URL
	 * @param method      请求方式[GET/POST]
	 * @param connTimeOut 超时时间
	 * @param readTimeout 超时时间
	 * @param encoding    编码格式
	 * @param params      参数,格式:username=admin&password=1
	 * @return
	 * @throws Exception
	 */
	private static String conn(String toUrl, String method, int connTimeOut, int readTimeout, String encoding, String params) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			URL url = new URL(toUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setRequestProperty("accept", "*/**");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
			conn.setRequestProperty("charset", encoding);
			conn.setConnectTimeout(connTimeOut);
			conn.setReadTimeout(readTimeout);
			conn.setDoOutput(true);
			conn.setDoInput(true);

			//传入参数
			writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), encoding));
			writer.write(params);
			writer.flush();
			writer.close();
			if (conn.getResponseCode() == 200) {
				// 获取返回值
				InputStream in = conn.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in, encoding));
				return reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("连接失败");
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
			if (writer != null) {
				writer.close();
			}
		}
		return null;
	}

	/**
	 * 将parameters中数据转换成用"&"链接的http请求参数形式
	 *
	 * @param parameters
	 */
	public static String generatorParamString(Map<String, String> parameters) {
		StringBuffer params = new StringBuffer();
		if (parameters != null) {
			for (Iterator<String> iter = parameters.keySet().iterator(); iter.hasNext(); ) {
				String name = iter.next();
				String value = parameters.get(name);
				params.append(name + "=");
				try {
					// 决定是否对http的get请求参数编码
//                  params.append(URLEncoder.encode(value, "UTF-8"));
					params.append(value);
				}
//              catch (UnsupportedEncodingException e) {
//                  throw new RuntimeException(e.getMessage(), e);
//              }
				catch (Exception e) {
					String message = String.format("'%s'='%s'", name, value);
					throw new RuntimeException(message, e);
				}
				if (iter.hasNext())
					params.append("&");
			}
		}
		return params.toString();
	}

}
