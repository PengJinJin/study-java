package com.design_pattern.state;

import java.util.concurrent.TimeUnit;

// 具体状态:减速跑动
public class SpeedDownState implements RunState {
	@Override
	public void run(Hero hero) {
		System.out.println("--------------SpeedDown---------------");
		try {
			// 假设减速4秒
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hero.setState(Hero.COMMON);
		System.out.println("------SpeedDown end, change to common------");
	}
}
