package root.com.design_pattern.adpter;

// 对象适配器
public class ObjAdapter implements Target {

	private Adaptee adaptee;

	public ObjAdapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void method1() {
		adaptee.am1();
	}

	@Override
	public void method2() {
		System.out.println("Object Adapter Method2");
	}
}
