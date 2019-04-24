package com.java.test.demo.dome1;

public class TestWater {

	public static void main(String[] args) {
		Water w = new WarmWater();// 多态、向上转型
		w.showTemperature();

		w = new IceWater();
		w.showTemperature();

		w = new HotWater();
		w.showTemperature();
		
		HotWater hw = (HotWater) w;
		hw.showTemperature();
		
	}

}
