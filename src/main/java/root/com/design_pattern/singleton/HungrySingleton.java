package root.com.design_pattern.singleton;

/**
 * 饿汉式单例实现<br/>
 * 在类创建时就已经创建了一个静态对象，以后不再改变
 */
public class HungrySingleton {

	private static final HungrySingleton instance = new HungrySingleton();

	private HungrySingleton() {

	}

	public static HungrySingleton getInstance() {
		return instance;
	}

}
