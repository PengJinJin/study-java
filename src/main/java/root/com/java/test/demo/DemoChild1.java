package root.com.java.test.demo;

public class DemoChild1 extends DemoParent1 {

	public DemoChild1() {
		super.getHand();// 调用父类方法
		this.getMouse();
		getEyes();
	}

	public DemoChild1(String name, String gender) {
		System.out.println("我叫" + name + ", 我是" + gender + "的");
	}

	/**
	 * 重写父类方法
	 */
	public void getMouse() {
		System.out.println("我的一张嘴给你");
	}

	/**
	 * 新增方法
	 */
	public void getEyes() {
		System.out.println("我的鼻子也给你");
	}

	protected String getStrin() {
		return "asds";
	}

	public DemoChild1 getDemo() {
		return new DemoChild1("ZETT", "男");
	}
	
	public String toString() {
		System.out.println("这是一个toString方法");
		return null;
	}

	public static void main(String[] args) {
		DemoChild1 d1 = new DemoChild1();
		// d1.getDemo();
		System.out.println(d1.getClass().getName());
		System.out.println(d1.getClass());
	}

}
