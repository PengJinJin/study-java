package root.com.design_pattern.abstract_factory;

//具体产品
public class InnerBanana extends Banana {
	@Override
	public void get() {
		System.out.println("inner banana");
	}
}
