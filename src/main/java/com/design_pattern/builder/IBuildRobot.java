package com.design_pattern.builder;

/**
 * 抽象建造者
 */
public interface IBuildRobot {

	void buildHead();
	void buildBody();
	void buildHand();
	void buildFoot();

	Robot getResult();

}
