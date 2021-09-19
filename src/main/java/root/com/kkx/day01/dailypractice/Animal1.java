package root.com.kkx.day01.dailypractice;

public interface Animal1 {

	void bark();

	default void b() {
		System.out.println("aa");
	}

}
