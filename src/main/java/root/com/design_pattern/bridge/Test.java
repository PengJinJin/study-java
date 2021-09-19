package root.com.design_pattern.bridge;

public class Test {
	public static void main(String[] args) {
		Client c = new Client();
		AbstractBridge bridge = new Bridge();

		// 实现A
		bridge.setImpl(new ConcreteImplA());
		c.use(bridge);

		// 实现B
		bridge.setImpl(new ConcreteImplB());
		c.use(bridge);

	}
}
