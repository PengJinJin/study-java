package root.com.design_pattern.adpter;

// 双向适配器测试
public class TwoWayAdapterTest {
	public static void main(String[] args) {
		TwoWayAdapter adapter = new TwoWayAdapter(new AdapteeRealize());
		adapter.specificRequest();
//		adapter.request();
		System.out.println("--------------");
		adapter = new TwoWayAdapter(new TargetRealize());
		adapter.request();
//		adapter.specificRequest();
	}
}

// 目标接口
interface TwoWayTarget {
	void request();
}

// 适配者接口
interface TwoWayAdaptee {
	void specificRequest();
}

class TargetRealize implements TwoWayTarget {
	@Override
	public void request() {
		System.out.println("Target request");
	}
}

// 适配者实现
class AdapteeRealize implements TwoWayAdaptee {
	@Override
	public void specificRequest() {
		System.out.println("adaptee specificRequest");
	}
}

// 双向适配器
class TwoWayAdapter implements TwoWayAdaptee, TwoWayTarget {
	private TwoWayAdaptee adaptee;
	private TwoWayTarget target;

	public TwoWayAdapter(TwoWayAdaptee adaptee) {
		this.adaptee = adaptee;
	}

	public TwoWayAdapter(TwoWayTarget target) {
		this.target = target;
	}

	@Override
	public void request() {
		target.request();
	}

	@Override
	public void specificRequest() {
		adaptee.specificRequest();
	}
}

