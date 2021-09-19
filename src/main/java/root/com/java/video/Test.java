package root.com.java.video;

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

	public static String TEMP_DIR = "D:/Download/temp/";// ����Ŀ¼
	public static int connTimeout = 30 * 60 * 1000;// ��ʱʱ��(����) ������������������ӵ�ʱ�䡣�������ָ����ʱ�䣬��û�������ӣ����쳣��
	public static int readTimeout = 30 * 60 * 1000;// ��ʱʱ��(����) �Ѿ���������������ӣ�����ʼ��ȡ�������Դ���������ָ����ʱ�䣬û�п��ܵ����ݱ��ͻ��˶�ȡ�����쳣��

	public static void main(String[] args) throws IOException {
//		String s1 = "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8";// ��ƵURL
		String s1 = "https://video-dev.github.io/streams/x36xhzz/url_0/193039199_mp4_h264_aac_hd_7.m3u8";// ��ƵURL
		download(s1, null);
	}

	/**
	 * ������Ƶ
	 *
	 * @param videoUrl m3u8��ַ
	 */
	public static synchronized void download(String videoUrl, String token) throws IOException {
		long startDownloadTime = System.currentTimeMillis();// ��ʼʱ��,��ʼ����ǰʱ��
		long lastDownloadTime = startDownloadTime; // ���һ��ʱ��
		BigDecimal totalDownloadSize = BigDecimal.ZERO; // �����ش�С,��ʼ��0
		BigDecimal lastDownloadSize = BigDecimal.ZERO;// �ϴ����ش�С,��ʼ��0
		String uuid = UUID.randomUUID().toString().replace("-", "");// ����UUID
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		File tfile = new File(TEMP_DIR + uuid);// ��ȡ����Ŀ¼,׷����Ŀ¼UUID
		if (!tfile.exists()) {
			// ����������򴴽�
			tfile.mkdirs();
		}
		M3U8 m3u8ByURL = getM3U8ByURL(videoUrl);// ������Ƶ��Ϣ
		if (m3u8ByURL == null) return;// �����Ƶ��ϢΪ��ֱ�ӷ���(��ʾʧ��)

		m3u8ByURL.setStartDownloadTime(format.format(new Date()));// ��ʼ����ʱ��(��ǰʱ��)
		String basePath = m3u8ByURL.getBasepath();
		boolean isPrint = false;
		for (M3U8.Ts m3U8Ts : m3u8ByURL.getTsList()) {// ������Ƶ����Ƭ
			FileOutputStream fos = null;
			InputStream inputStream = null;
			File floder = new File(TEMP_DIR + uuid + File.separator + (m3U8Ts.getFile().substring(0, m3U8Ts.getFile().indexOf("/"))));
			if (!floder.exists()) {
				floder.mkdir();
			}
			try {
				File file = new File(TEMP_DIR + uuid + File.separator + m3U8Ts.getFile());// ׼������Ƭ�ļ������Ŀ¼+�ļ���
				if (!file.exists()) {// ����ֻ������û�����ص�,���ع��ľͲ�����
					// ��URL��ȡ��-------start
					URL url = new URL(basePath + m3U8Ts.getFile());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(connTimeout);
					conn.setReadTimeout(readTimeout);
					// ��URL��ȡ��-------end
					if (conn.getResponseCode() == 200) {
						inputStream = conn.getInputStream();
//						fos = new FileOutputStream(file);// ���Զ������ļ�
						int len;
						byte[] buf = new byte[1024];// ����һ��������,��СΪ1024 byte
						while ((len = inputStream.read(buf)) != -1) {
//							fos.write(buf, 0, len);// д������,�����ļ�
							totalDownloadSize = totalDownloadSize.add(BigDecimal.valueOf(len));// �ӷ�
							if (System.currentTimeMillis() - lastDownloadTime >= 1000) {
								// �ٶ� = �����ش�С - �ϴ����ش�С / 1024 (����2λС��)
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
			} finally {// ����
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
		m3u8ByURL.setEndDownloadTime(format.format(new Date()));// �������ʱ��(��ǰʱ��)
		BigDecimal total = totalDownloadSize.divide(BigDecimal.valueOf(1024), 2, RoundingMode.HALF_UP);
		m3u8ByURL.setDownLoadSize(total);// �����ش�С
		// ƽ���ٶ� = ��ǰʱ�� - ��ʼʱ�� / 1000���� (�������뱣��2λС��)
		m3u8ByURL.setAvgSpeed(BigDecimal.valueOf(System.currentTimeMillis() - startDownloadTime).divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP));
		//System.out.println("�ļ��������!");
		//System.out.println(m3u8ByURL);
		writeFileContent(TEMP_DIR + uuid + File.separator + uuid + ".txt", "�ļ��������!\n" + m3u8ByURL);
		mergeFiles(tfile.listFiles(), "test.ts");
	}

	/**
	 * ��װ��Ƶ��Ϣ
	 *
	 * @param m3u8URL ��Ƶ����
	 * @return
	 */
	public static M3U8 getM3U8ByURL(String m3u8URL) {
		try {
			// �½�һ�����Ӳ�ֱ�ӷ���
			HttpURLConnection conn = (HttpURLConnection) new URL(m3u8URL).openConnection();
			// ������ӳɹ�
			if (conn.getResponseCode() == 200) {
				// ��ȡ��������,ʵ���Ͼ��� "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8";
				String realUrl = conn.getURL().toString();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));// ͨ������ȡ������������Ϣ
				// ��"http://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8"���
				// "http://playertest.longtailvideo.com/adaptive/bipbop/gear4/"
				String basepath = realUrl.substring(0, realUrl.lastIndexOf("/") + 1);
				M3U8 ret = new M3U8();
				ret.setBasepath(basepath);// ��ֵ

				String line;
				float seconds = 0;
				int mIndex;
				// ��ȡ��������,ÿ�ζ�ȡһ��,ֱ��û��
				while ((line = reader.readLine()) != null) {
					// ����m3u8�ļ�����#EXTINF:10, no desc��һ��
					// ������þ���ȡ��  10��  ���Ӧ���Ǳ�ʾ��
					if (line.startsWith("#")) {// ����m3u8��ʽ���ļ�,ͨ�������ҵ����еķ�Ƭ�ļ�
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
						// �������m3u8��β����ʾ����m3u8�ļ���������ݹ�����
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
	 * ����Ƭ��˳���������������
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
	 * ���ļ���д������
	 *
	 * @param filepath �ļ�·��������
	 * @param newstr   д�������
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFileContent(String filepath, String newstr) throws IOException {
		boolean bool = false;
		String filein = newstr + "\r\n";//��д����У�����
		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
//		PrintWriter pw = null;
		try {
			File file = new File(filepath);//�ļ�·��(�����ļ�����)
			if (!file.exists()) {
				file.createNewFile();
			}
			//���ļ�����������
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			/*
			StringBuffer buffer = new StringBuffer();
			//�ļ�ԭ������
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				// ������֮��ķָ��� �൱�ڡ�\n��
				buffer = buffer.append(System.getProperty("line.separator"));
			}
			buffer.append(filein);
			*/

			fos = new FileOutputStream(file, true);// ׷��д��
			fos.write(filein.getBytes());
//			pw = new PrintWriter(fos);
//			pw.write(buffer.toString().toCharArray());
//			pw.write();
//			pw.flush();
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//��Ҫ���ǹر�
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

