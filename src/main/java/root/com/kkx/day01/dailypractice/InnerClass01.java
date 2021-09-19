package root.com.kkx.day01.dailypractice;

public class InnerClass01 {

	int a;
	static int b;

	static void c() {

	}

	void d() {

	}


	static class Static {

		static int c;

		static void f() {
			InnerClass01 i = new InnerClass01();
			int h = i.a;
		}
	}

	class Inner1 {
		int c;
	}


}
