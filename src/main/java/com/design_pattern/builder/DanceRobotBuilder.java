package com.design_pattern.builder;

/**
 * 具体建造者
 */
public class DanceRobotBuilder implements IBuildRobot {

	Robot robot;

	public DanceRobotBuilder() {
		robot = new Robot();
	}


	@Override
	public void buildHead() {
		robot.setHead("writing POPPING");
	}

	@Override
	public void buildBody() {
		robot.setBody("titanium alloy body");
	}

	@Override
	public void buildHand() {
		robot.setHand("titanium alloy hand");
	}

	@Override
	public void buildFoot() {
		robot.setFoot("titanium alloy foot");
	}

	@Override
	public Robot getResult() {
		return robot;
	}
}
