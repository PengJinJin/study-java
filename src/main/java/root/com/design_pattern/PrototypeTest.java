package root.com.design_pattern;

/**
 * 原型模式<br/>
 * 用已创建的实例作为原型，通过复制这个原型来创建一个和原型类似的新对象<br/>
 * <p>
 * 原型模式包含以下主要角色。
 * 1.抽象原型类：规定了具体原型对象必须实现的接口。
 * 2.具体原型类：实现抽象原型类的 clone() 方法，它是可被复制的对象。
 * 3.访问类：使用具体原型类中的 clone() 方法来复制新的对象。
 * </p>
 */
public class PrototypeTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		Realizetype obj = new Realizetype();
		Realizetype obj1 = obj.clone();
		System.out.println("obj == obj1: " + (obj == obj1));
	}

}

// 具体原型类
class Realizetype implements Cloneable {
	Realizetype() {
		System.out.println("具体原型创建成功！");
	}

	@Override
	protected Realizetype clone() throws CloneNotSupportedException {
		System.out.println("复制成功！");
		return (Realizetype) super.clone();
	}
}
