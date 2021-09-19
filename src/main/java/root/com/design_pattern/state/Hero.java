package root.com.design_pattern.state;

/**
 * 环境角色:英雄类
 */
public class Hero {

	public static final RunState COMMON = new CommonState();//正常状态

	public static final RunState SPEED_UP = new SpeedUpState();//加速状态

	public static final RunState SPEED_DOWN = new SpeedDownState();//减速状态

	public static final RunState SWIM = new SwimState();//眩晕状态

	private RunState state = COMMON;//默认是正常状态

	private Thread runThread;//跑动线程

	//设置状态
	public void setState(RunState state) {
		this.state = state;
	}

	// 停止跑动
	public void stopRun() {
		if (isRunning()) {
			runThread.interrupt();
		}
		System.out.println("--------------stopRun---------------");
	}

	// run!
	public void startRun() {
		if (isRunning()) {
			return;
		}
		final Hero hero = this;
		runThread = new Thread(() -> {
			// 如果跑动线程不是阻塞状态
			while (!runThread.isInterrupted()) {
				state.run(hero);
			}
		});
		System.out.println("--------------startRun---------------");
		runThread.start();
	}

	/**
	 * 判断是否在跑动
	 */
	private boolean isRunning() {
		return runThread != null && !runThread.isInterrupted();
	}
}
