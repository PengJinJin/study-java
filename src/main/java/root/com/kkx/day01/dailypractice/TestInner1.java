package root.com.kkx.day01.dailypractice;

public class TestInner1 {

	public static void main(String[] args) {
		InnerClass01.Static s = new InnerClass01.Static();

		InnerClass01 i01 = new InnerClass01();
		InnerClass01.Inner1 i = i01.new Inner1();
	}

}
