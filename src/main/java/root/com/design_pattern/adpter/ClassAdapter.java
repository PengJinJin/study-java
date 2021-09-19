package root.com.design_pattern.adpter;

// 类适配器
public class ClassAdapter extends Adaptee implements Target {

	@Override
	public void method1() {

	}

	@Override
	public void method2() {
//		am1();
		System.out.println("Class Adaptee Method2");
	}

}
