package root.com.design_pattern.observer;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ObserverDemo {

	public static void main(String[] args) {
		// 主体
		Subject publisher = Publisher.builder().build();
		publisher.initObservers();

		// 观察者注册
		Observer os1 = Subscriber1.builder().build();
		publisher.registerObserver(os1);
		Observer os2 = Subscriber2.builder().build();
		publisher.registerObserver(os2);

		// 通知当前已注册的观察者
		publisher.notifyObservers();

		// 移除观察者
		publisher.removeObserver(os1);

		// 通知当前已注册的观察者
		publisher.notifyObservers();
	}

}

// 抽象观察者
interface Observer {
	void update(String edition);
}

// 具体观察者1
@Data
@Builder
class Subscriber1 implements Observer {

	@Override
	public void update(String edition) {
		System.out.println("Subscriber1 Received..");
	}
}

// 具体观察者2
@Data
@Builder
class Subscriber2 implements Observer {

	@Override
	public void update(String edition) {
		System.out.println("Subscriber2 Received...");
	}
}

// 抽象主题
interface Subject {
	//注册主体
	void registerObserver(Observer observer);

	// 移除订阅
	void removeObserver(Observer observer);

	// 通知所有的观察者
	void notifyObservers();

	// 初始化主体相关信息
	void initObservers();
}

// 具体主题
@Data
@Builder
class Publisher implements Subject {

	// 已注册的观察者列表
	private List<Observer> list = new ArrayList<>();

	@Override
	public void registerObserver(Observer observer) {
		System.out.println("registerObserver: " + observer);
		list.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		System.out.println("removeObserver: " + observer);
		list.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : list) {
			o.update("message notify: " + o);
		}
	}

	@Override
	public void initObservers() {
		if (null == list) {
			list = new ArrayList<>();
		}
	}
}
