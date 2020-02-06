package com.design_pattern.state;

import java.util.concurrent.TimeUnit;

// 具体状态:加速跑动
public class SpeedUpState implements RunState {
	@Override
	public void run(Hero hero) {
		System.out.println("--------------SpeedUp---------------");
		try {
			// 假设加速4秒
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hero.setState(Hero.COMMON);
		System.out.println("------SpeedUp end, change to common------");
	}
}
