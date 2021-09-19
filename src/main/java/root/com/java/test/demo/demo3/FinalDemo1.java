package root.com.java.test.demo.demo3;

public class FinalDemo1 {
	public static void main(String[] args) {
		Sub s = new Sub();
		s.doit();
		Parents p = s;
//		p.doit();
		p.doit2();
		p.doit3();
	}
}

class Parents {
	private final void doit() {
		System.out.println("父类doit");
	}

	final void doit2() {
		System.out.println("父类doit222");
	}

	public void doit3() {
		System.out.println("父类doit333");
	}
}

class Sub extends Parents {
	public final void doit() {
		System.out.println("子类doit");
	}
/*
	final void doit2() {
		System.out.print("子类doit222");
	}
*/
	public void doit3() {
		System.out.println("父类doit333");
	}

}
