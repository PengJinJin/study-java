package root.com.java.test;

public class NewFeaturesTester {
	public static void main(String args[]) {
		NewFeaturesTester tester = new NewFeaturesTester();

		// �������������ı��ʽ
		MathOperation addition = (int a, int b) -> a + b;

		// û�����������ı��ʽ
		MathOperation subtraction = (a, b) -> a - b;

		// ���д����š����з������ı��ʽ
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};

		// û�д����ź�return���ı��ʽ
		MathOperation division = (int a, int b) -> a / b;

		// ������
		System.out.println("10 + 5 = " + tester.operate(100, 2, addition));
		System.out.println("10 - 5 = " + tester.operate(100, 2, subtraction));
		System.out.println("10 x 5 = " + tester.operate(100, 2, multiplication));
		System.out.println("10 / 5 = " + tester.operate(100, 2, division));

		// û�����ŵı��ʽ
		GreetingService greetService1 = message -> System.out.println("Hello " + message);

		// �����ŵı��ʽ
		GreetingService greetService2 = (message) -> System.out.println("Hello " + message);

		// ����sayMessage����������
		greetService1.sayMessage("Shiyanlou");
		greetService2.sayMessage("Classmate");
	}

	// �����Ƕ����һЩ�ӿںͷ���
	@FunctionalInterface
	interface MathOperation {
		int operation(int a, int b);
	}
	
	interface GreetingService {
		void sayMessage(String message);
	}

	private int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}
