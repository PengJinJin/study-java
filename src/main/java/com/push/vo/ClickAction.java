package com.push.vo;

public class ClickAction extends com.tencent.xinge.bean.ClickAction {
	//动作类型，1，打开activity或app本身；2，打开浏览器；3，打开Intent
	private int action_type = ActionType.OPEN_ACTIVITY_BY_CLASS_NAME.getType();

	private String activity = "";

	private Browser browser;

	//activity属性，只针对action_type=1的情况
	private AtyAttr aty_attr;

	//客户端 Android SDK版本需要大于等于3.2.3，然后在客户端的intent配置data标签，并设置scheme属性
	private String intent;

	public int getAction_type() {
		return action_type;
	}

	public void setAction_type(int action_type) {
		this.action_type = action_type;
	}

	public String getActivity() {
		return activity;
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public AtyAttr getAty_attr() {
		return aty_attr;
	}

	public void setAty_attr(AtyAttr aty_attr) {
		this.aty_attr = aty_attr;
	}

	public String getIntent() {
		return intent;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}
}
