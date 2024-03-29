package root.com.java.test.apple.service.impl;

import java.util.Objects;

import root.com.java.test.apple.service.ApplePredicate;
import root.com.java.test.vo.Apple;

public class AppleGreenColorPredicate implements ApplePredicate {

	@Override
	public boolean test(Apple apple) {
		return Objects.equals(apple.getColor(), "Green");
	}

}
