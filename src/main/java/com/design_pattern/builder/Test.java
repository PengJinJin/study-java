package com.design_pattern.builder;

public class Test {

	public static void main(String[] args) {
		Director d = new Director();
		Robot robot = d.createRobotByDirector(new DanceRobotBuilder());
		System.out.println(robot);
		System.out.println("Robot build success, congratulations");
	}

}
