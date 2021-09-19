package root.com.design_pattern.facade;

public class FacadePattern {

	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.fMethod();
	}

}

// 外观角色
class Facade {

	private SubSys1 s1 = new SubSys1();
	private SubSys2 s2 = new SubSys2();
	private SubSys3 s3 = new SubSys3();

	public void fMethod() {
		s1.m1();
		s2.m2();
		s3.m3();
	}

}

//子系统角色1
class SubSys1 {
	public void m1() {
		System.out.println("SubSys1 m1 invoke!");
	}
}

//子系统角色2
class SubSys2 {
	public void m2() {
		System.out.println("SubSys2 m2 invoke!");
	}
}

//子系统角色3
class SubSys3 {
	public void m3() {
		System.out.println("SubSys3 m3 invoke!");
	}
}