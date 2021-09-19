package root.com.design_pattern.state;

import java.util.concurrent.TimeUnit;

/**
 * 这个demo是模仿LOL英雄的状态，比如正常，眩晕，加速，减速等等
 */
public class StatePatternDemo {
	public static void main(String[] args) throws InterruptedException {
		Hero hero = new Hero();
		hero.startRun();
		// 加速
		hero.setState(Hero.SPEED_UP);
		TimeUnit.SECONDS.sleep(5);
		// 减速
		hero.setState(Hero.SPEED_DOWN);
		TimeUnit.SECONDS.sleep(5);
		// 眩晕
		hero.setState(Hero.SWIM);
		TimeUnit.SECONDS.sleep(3);
		// 停
		hero.stopRun();
	}
}


