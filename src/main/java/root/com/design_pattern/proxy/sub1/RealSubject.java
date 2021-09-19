package root.com.design_pattern.proxy.sub1;

public class RealSubject implements Subject {
	@Override
	public void request() {
		System.out.println("Visit RealSubject method...");
	}
}
