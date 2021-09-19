package root.com.java.video;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ��ȡ��Ƶ��Ϣ
 */
public class M3U8 {
	private String basepath;// URL
	private List<Ts> tsList = new ArrayList<>();// ��Ƭ��Ϣ
	private long startTime;// ��ʼʱ��
	private long endTime;// ����ʱ��
	private String startDownloadTime;// ��ʼ����ʱ��
	private String endDownloadTime;// ��������ʱ��
	private BigDecimal downLoadSize;// ���ش�С
	private BigDecimal avgSpeed;// ���ش�С

	public BigDecimal getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(BigDecimal avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public List<Ts> getTsList() {
		return tsList;
	}

	public void setTsList(List<Ts> tsList) {
		this.tsList = tsList;
	}

	public void addTs(Ts ts) {
		this.tsList.add(ts);
	}

	public String getStartDownloadTime() {
		return startDownloadTime;
	}

	public void setStartDownloadTime(String startDownloadTime) {
		this.startDownloadTime = startDownloadTime;
	}

	public String getEndDownloadTime() {
		return endDownloadTime;
	}

	public void setEndDownloadTime(String endDownloadTime) {
		this.endDownloadTime = endDownloadTime;
	}

	public BigDecimal getDownLoadSize() {
		return downLoadSize;
	}

	public void setDownLoadSize(BigDecimal downLoadSize) {
		this.downLoadSize = downLoadSize;
	}

	/**
	 * ��ȡ��ʼʱ��,��һ����Ƭ�Ŀ�ʼʱ��
	 *
	 * @return
	 */
	public long getStartTime() {
		if (tsList.size() > 0) {
			Collections.sort(tsList);
			startTime = tsList.get(0).getLongDate();
			return startTime;
		}
		return 0;
	}

	/**
	 * ��ȡ����ʱ��(���һ�η�Ƭ�ĳ���ʱ��,��λ:����)
	 *
	 * @return
	 */
	public long getEndTime() {
		if (tsList.size() > 0) {
			Ts m3U8Ts = tsList.get(tsList.size() - 1);
			endTime = m3U8Ts.getLongDate() + (long) (m3U8Ts.getSeconds() * 1000);
			return endTime;
		}
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("basepath: ").append(basepath);
		sb.append("\n\nts_file_length: ").append(tsList.size());
		for (Ts ts : tsList) {
			sb.append("\nts_file_name = ").append(ts);
		}
		sb.append("\n\nstartTime = ").append(getStartTime());
		sb.append("\n\nendTime = ").append(getEndTime());
		sb.append("\n\nstartDownloadTime = ").append(startDownloadTime);
		sb.append("\n\nendDownloadTime = ").append(endDownloadTime);
		sb.append("\n\ndownLoadSize = ").append(downLoadSize).append("KB");
		sb.append("\n\navgSpeed = ").append(avgSpeed).append("KB/S");
		return sb.toString();
	}

	public static class Ts implements Comparable<Ts> {
		private String file;
		private float seconds;

		public Ts(String file, float seconds) {
			this.file = file;
			this.seconds = seconds;
		}

		public String getFile() {
			return file;
		}

		public void setFile(String file) {
			this.file = file;
		}

		public float getSeconds() {
			return seconds;
		}

		public void setSeconds(float seconds) {
			this.seconds = seconds;
		}

		@Override
		public String toString() {
			return file + " (" + seconds + "sec)";
		}

		/**
		 * ��ȡʱ��
		 */
		public long getLongDate() {
			try {
				return Long.parseLong(file.substring(0, file.lastIndexOf(".")));
			} catch (Exception e) {
				return 0;
			}
		}

		@Override
		public int compareTo(Ts o) {
			return file.compareTo(o.file);
		}
	}

}
