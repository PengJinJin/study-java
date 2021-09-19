package root.com.design_pattern.abstract_factory;

// 具体产品
public class InnerApple extends Apple {
	@Override
	public void get() {
		System.out.println("InnerApple~");
	}
}
