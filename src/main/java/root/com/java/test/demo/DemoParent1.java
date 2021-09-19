package root.com.java.test.demo;

public class DemoParent1 {
	
	public DemoParent1() {
		System.out.println("父类无参构造方法");
	}

	public void getHand() {
		System.out.println("我的手给你");
	}

	public void getMouse() {
		System.out.println("我的嘴给你");
	}
	
	protected String getStrin() {
		return "asda";
	}

	public DemoParent1 getDemo() {
		return new DemoParent1();
	}

}
