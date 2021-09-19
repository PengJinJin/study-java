package root.com.design_pattern.decorator;

// 具体构件A
public class ConComponentA implements AbsComponent {
	@Override
	public void method() {
		System.out.println("Concrete ComponentA");
	}
}
