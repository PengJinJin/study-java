package root.com.design_pattern.builder;

/**
 * Director
 */
public class Director {

	public Robot createRobotByDirector(IBuildRobot ibr) {
		ibr.buildBody();
		ibr.buildFoot();
		ibr.buildHand();
		ibr.buildHead();
		return ibr.getResult();
	}

}
