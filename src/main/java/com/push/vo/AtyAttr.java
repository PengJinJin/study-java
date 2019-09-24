package com.push.vo;

public class AtyAttr {
	private int atyAttrIntentFlag = 0;
	private int atyAttrPendingIntentFlag = 0;

	public int getIf() {
		return atyAttrIntentFlag;
	}

	public void setIf(int m_if) {
		this.atyAttrIntentFlag = m_if;
	}

	public int getPf() {
		return atyAttrPendingIntentFlag;
	}

	public void setPf(int atyAttrPendingIntentFlag) {
		this.atyAttrPendingIntentFlag = atyAttrPendingIntentFlag;
	}

}
