package com.java.video;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 获取视频信息
 */
public class M3U8 {
	private String basepath;// URL
	private List<Ts> tsList = new ArrayList<>();// 切片信息
	private long startTime;// 开始时间
	private long endTime;// 结束时间
	private String startDownloadTime;// 开始下载时间
	private String endDownloadTime;// 结束下载时间
	private BigDecimal downLoadSize;// 下载大小
	private BigDecimal avgSpeed;// 下载大小

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
	 * 获取开始时间,第一个分片的开始时间
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
	 * 获取结束时间(最后一段分片的持续时间,单位:毫秒)
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
		 * 获取时间
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
