package root.com.java.test;

public class KuaidiVo {
	private String com;
	private String nu;
	private String show;
	private String muti;
	private String order = "desc";

	public KuaidiVo(String com, String nu, String show, String muti, String order) {
		this.com = com;
		this.nu = nu;
		this.show = show;
		this.muti = muti;
		this.order = order;
	}

	public String getCom() {
		return com;
	}

	public void setCom(String com) {
		this.com = com;
	}

	public String getNu() {
		return nu;
	}

	public void setNu(String nu) {
		this.nu = nu;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getMuti() {
		return muti;
	}

	public void setMuti(String muti) {
		this.muti = muti;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer("http://api.kuaidi100.com/api?");
		buffer.append("id=96a60866c6ad40a8").append("&com=").append(com).append("&nu=").append(nu).append("&valicode=")
				.append("&show=").append(show).append("&muti=").append(muti).append("&order=").append(order);
		return buffer.toString();
	}
}
