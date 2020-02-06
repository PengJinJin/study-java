package com.design_pattern.state;


import java.util.concurrent.TimeUnit;

// 具体状态:眩晕类
public class SwimState implements RunState {
	@Override
	public void run(Hero hero) {
		System.out.println("--------------swimming...can't run---------------");
		try {
			// 眩晕2秒
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hero.setState(Hero.COMMON);
		System.out.println("--------------Swim end, change to COMMON---------------");
	}
}
