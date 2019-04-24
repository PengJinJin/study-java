package com.java.video;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Test {

	public static String TEMP_DIR = "D:/Download/temp/";// 下载目录
	public static int connTimeout = 30 * 60 * 1000;// 超时时间(毫秒) 用来与服务器建立连接的时间。如果到了指定的时间，还没建立连接，则报异常。
	public static int readTimeout = 30 * 60 * 1000;// 超时时间(毫秒) 已经与服务器建立连接，并开始读取服务端资源。如果到了指定的时间，没有可能的数据被客户端读取，则报异常。

	public static void main(String[] args) throws IOException {
//		String s1 = "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8";// 视频URL
		String s1 = "https://video-dev.github.io/streams/x36xhzz/url_0/193039199_mp4_h264_aac_hd_7.m3u8";// 视频URL
		download(s1, null);
	}

	/**
	 * 下载视频
	 *
	 * @param videoUrl m3u8地址
	 */
	public static synchronized void download(String videoUrl, String token) throws IOException {
		long startDownloadTime = System.currentTimeMillis();// 开始时间,初始化当前时间
		long lastDownloadTime = startDownloadTime; // 最后一次时间
		BigDecimal totalDownloadSize = BigDecimal.ZERO; // 总下载大小,初始化0
		BigDecimal lastDownloadSize = BigDecimal.ZERO;// 上次下载大小,初始化0
		String uuid = UUID.randomUUID().toString().replace("-", "");// 生成UUID
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		File tfile = new File(TEMP_DIR + uuid);// 获取下载目录,追加子目录UUID
		if (!tfile.exists()) {
			// 如果不存在则创建
			tfile.mkdirs();
		}
		M3U8 m3u8ByURL = getM3U8ByURL(videoUrl);// 创建视频信息
		if (m3u8ByURL == null) return;// 如果视频信息为空直接返回(表示失败)

		m3u8ByURL.setStartDownloadTime(format.format(new Date()));// 开始下载时间(当前时间)
		String basePath = m3u8ByURL.getBasepath();
		boolean isPrint = false;
		for (M3U8.Ts m3U8Ts : m3u8ByURL.getTsList()) {// 遍历视频的切片
			FileOutputStream fos = null;
			InputStream inputStream = null;
			File floder = new File(TEMP_DIR + uuid + File.separator + (m3U8Ts.getFile().substring(0, m3U8Ts.getFile().indexOf("/"))));
			if (!floder.exists()) {
				floder.mkdir();
			}
			try {
				File file = new File(TEMP_DIR + uuid + File.separator + m3U8Ts.getFile());// 准备把切片文件保存的目录+文件名
				if (!file.exists()) {// 这里只操作还没有下载的,下载过的就不管了
					// 打开URL读取流-------start
					URL url = new URL(basePath + m3U8Ts.getFile());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(connTimeout);
					conn.setReadTimeout(readTimeout);
					// 打开URL读取流-------end
					if (conn.getResponseCode() == 200) {
						inputStream = conn.getInputStream();
//						fos = new FileOutputStream(file);// 会自动创建文件
						int len;
						byte[] buf = new byte[1024];// 设置一个缓冲区,大小为1024 byte
						while ((len = inputStream.read(buf)) != -1) {
//							fos.write(buf, 0, len);// 写入流中,保存文件
							totalDownloadSize = totalDownloadSize.add(BigDecimal.valueOf(len));// 加法
							if (System.currentTimeMillis() - lastDownloadTime >= 1000) {
								// 速度 = 总下载大小 - 上次下载大小 / 1024 (保留2位小数)
								BigDecimal speed = totalDownloadSize.subtract(lastDownloadSize).divide(BigDecimal.valueOf(1024), 2, RoundingMode.HALF_UP);
								String content = "download_speed: " + format.format(new Date()) + " " + speed + "(KB/s)";
								writeFileContent(TEMP_DIR + uuid + File.separator + uuid + ".txt", content);
								//System.out.println(content);
								lastDownloadTime = System.currentTimeMillis();
								lastDownloadSize = totalDownloadSize;
							}
						}
					}
				}
			} catch (Exception e) {

				e.printStackTrace();
			} finally {// 关流
				try {
					if (inputStream != null) {
						inputStream.close();
					}
					if (fos != null) {
						fos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		m3u8ByURL.setEndDownloadTime(format.format(new Date()));// 下载完成时间(当前时间)
		BigDecimal total = totalDownloadSize.divide(BigDecimal.valueOf(1024), 2, RoundingMode.HALF_UP);
		m3u8ByURL.setDownLoadSize(total);// 总下载大小
		// 平均速度 = 当前时间 - 开始时间 / 1000毫秒 (四舍五入保留2位小数)
		m3u8ByURL.setAvgSpeed(BigDecimal.valueOf(System.currentTimeMillis() - startDownloadTime).divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP));
		//System.out.println("文件下载完毕!");
		//System.out.println(m3u8ByURL);
		writeFileContent(TEMP_DIR + uuid + File.separator + uuid + ".txt", "文件下载完毕!\n" + m3u8ByURL);
		mergeFiles(tfile.listFiles(), "test.ts");
	}

	/**
	 * 封装视频信息
	 *
	 * @param m3u8URL 视频链接
	 * @return
	 */
	public static M3U8 getM3U8ByURL(String m3u8URL) {
		try {
			// 新建一个链接并直接访问
			HttpURLConnection conn = (HttpURLConnection) new URL(m3u8URL).openConnection();
			// 如果连接成功
			if (conn.getResponseCode() == 200) {
				// 获取下载链接,实际上就是 "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8";
				String realUrl = conn.getURL().toString();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));// 通过流读取服务器返回信息
				// 把"http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8"变成
				// "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/"
				String basepath = realUrl.substring(0, realUrl.lastIndexOf("/") + 1);
				M3U8 ret = new M3U8();
				ret.setBasepath(basepath);// 赋值

				String line;
				float seconds = 0;
				int mIndex;
				// 读取流的内容,每次读取一行,直到没有
				while ((line = reader.readLine()) != null) {
					// 比如m3u8文件里有#EXTINF:10, no desc这一行
					// 这段作用就是取到  10，  这个应该是表示秒
					if (line.startsWith("#")) {// 解析m3u8格式的文件,通过解析找到所有的分片文件
						if (line.startsWith("#EXTINF:")) {
							line = line.substring(8);
							if ((mIndex = line.indexOf(",")) != -1) {
								line = line.substring(0, mIndex);
							}
							try {
								seconds = Float.parseFloat(line);
							} catch (Exception e) {
								seconds = 0;
							}
						}
						continue;
					}
					if (line.endsWith("m3u8")) {
						// 如果是以m3u8结尾，表示还有m3u8文件，则继续递归下载
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

	/**
	 * 给分片按顺序重新排序和命名
	 *
	 * @param fpaths
	 * @param resultPath
	 * @return
	 */
	public static boolean mergeFiles(File[] fpaths, String resultPath) {
		if (fpaths == null || fpaths.length < 1) {
			return false;
		}

		if (fpaths.length == 1) {
			return fpaths[0].renameTo(new File(resultPath));
		}
		for (File fpath1 : fpaths) {
			if (!fpath1.exists() || !fpath1.isFile()) {
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

		// for (int i = 0; i < fpaths.length; i ++) {
		// fpaths[i].delete();
		// }

		return true;
	}

	/**
	 * 向文件中写入内容
	 *
	 * @param filepath 文件路径与名称
	 * @param newstr   写入的内容
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFileContent(String filepath, String newstr) throws IOException {
		boolean bool = false;
		String filein = newstr + "\r\n";//新写入的行，换行
		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
//		PrintWriter pw = null;
		try {
			File file = new File(filepath);//文件路径(包括文件名称)
			if (!file.exists()) {
				file.createNewFile();
			}
			//将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			/*
			StringBuffer buffer = new StringBuffer();
			//文件原有内容
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				// 行与行之间的分隔符 相当于“\n”
				buffer = buffer.append(System.getProperty("line.separator"));
			}
			buffer.append(filein);
			*/

			fos = new FileOutputStream(file, true);// 追加写入
			fos.write(filein.getBytes());
//			pw = new PrintWriter(fos);
//			pw.write(buffer.toString().toCharArray());
//			pw.write();
//			pw.flush();
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//不要忘记关闭
//			if (pw != null) {
//				pw.close();
//			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return bool;
	}
}

