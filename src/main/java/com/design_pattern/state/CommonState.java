package com.design_pattern.state;

// 具体状态:正常跑动
public class CommonState implements RunState {
	@Override
	public void run(Hero hero) {
		//正常跑动则不打印内容，否则会刷屏
	}
}
