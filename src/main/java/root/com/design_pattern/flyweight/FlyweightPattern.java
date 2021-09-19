package root.com.design_pattern.flyweight;

import java.util.HashMap;

public class FlyweightPattern {
	public static void main(String[] args) {
		FlyweightFactory factory = new FlyweightFactory();
		Flyweight f_01 = factory.getFlyweight("a");
		Flyweight f_02 = factory.getFlyweight("a");
		Flyweight f_03 = factory.getFlyweight("a");

		Flyweight f_11 = factory.getFlyweight("b");
		Flyweight f_12 = factory.getFlyweight("b");

		f_01.operation(new Unsharable("~~~first call a"));
		f_02.operation(new Unsharable("~~~second call a"));
		f_03.operation(new Unsharable("~~~third call a"));

		f_11.operation(new Unsharable("---first call b"));
		f_12.operation(new Unsharable("---second call b"));

		System.out.println(">>>>Factory size: " + factory.getSize());
	}
}

// 非享元角色（外部状态）
class Unsharable {
	private String info;

	public Unsharable(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}

// 抽象享元角色
interface Flyweight {
	void operation(Unsharable outState);
}

// 具体享元角色
class ConcreteFlyweight implements Flyweight {

	private String key;

	public ConcreteFlyweight(String key) {
		this.key = key;
		System.out.println("ConcreteFlyweight +" + key + "+ has been created");
	}

	@Override
	public void operation(Unsharable outState) {
		System.out.println("ConcreteFlyweight +" + key + "+ has been invoke。");
		System.out.println("Unsharable Flyweight is " + outState.getInfo());
	}
}

//享元工厂角色
class FlyweightFactory {
	private HashMap<String, Flyweight> map = new HashMap<>();

	public Flyweight getFlyweight(String key) {
		Flyweight f = map.get(key);
		if (f != null) {
			System.out.println("Concrete Flyweight +" + key + "+ han been exists!");
		} else {
			f = new ConcreteFlyweight(key);
			map.put(key, f);
		}
		return f;
	}

	public int getSize() {
		return map.size();
	}
}