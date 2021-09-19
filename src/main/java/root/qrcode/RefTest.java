package root.qrcode;

public class RefTest {

	public static void main(String[] args) {
//		int a = 1;
//		add(a);
//		System.out.println(a);
//		String str = "abc";
//		append(str);
//		System.out.println(str);
		O o = new O("Zhang San", 10);
		update(o);
		System.out.println(o);
		test1();
	}

	public static int test1() {
		int a = 1;
		try {
			a += 1;
			return a;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(222);
		}
		return 33;
	}

	public static void add(int a) {
		a += 5;
		System.out.println(a + "=====add");
	}

	public static void append(String str) {
		str += " append";
		System.out.println(str + "========");
	}

	public static void update(O o) {
		o.setAge(20);
		o.setName("Tony");
	}

}

class O {
	private String name;
	private int age;

	public O(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "name: " + name + ", age: " + age;
	}
}
