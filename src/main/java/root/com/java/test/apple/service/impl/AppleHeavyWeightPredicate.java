package root.com.java.test.apple.service.impl;

import root.com.java.test.apple.service.ApplePredicate;
import root.com.java.test.vo.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}

}
