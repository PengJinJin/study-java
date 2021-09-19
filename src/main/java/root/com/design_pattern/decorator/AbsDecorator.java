package root.com.design_pattern.decorator;

// 抽象装饰
public class AbsDecorator implements AbsComponent {

	private AbsComponent component;

	public AbsDecorator(AbsComponent component) {
		this.component = component;
	}

	@Override
	public void method() {
		component.method();
	}

}
