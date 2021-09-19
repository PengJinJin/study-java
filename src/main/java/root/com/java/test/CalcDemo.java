package root.com.java.test;

import java.util.Scanner;

public class CalcDemo {
	
	private static Scanner input;

	public static void main(String[] args) {
		int input1, input2;
		System.out.println("输入第一个整数");
		input1 = input();
		System.out.println("输入第2个整数");
		input2 = input();
		System.out.println("输入+、-、*、/字符");
		input = new Scanner(System.in);
		try {
			switch (input.nextLine()) {
			case "+":
				System.out.println(input1 + " + " + input2 + " = " + (input1 + input2));
				break;
			case "-":
				System.out.println(input1 + " - " + input2 + " = " + (input1 - input2));
				break;
			case "*":
				System.out.println(input1 + " * " + input2 + " = " + (input1 * input2));
				break;
			case "/":
				System.out.println(input1 + " / " + input2 + " = " + (input1 / input2));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.err.println("异常了");
			System.exit(0);
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	/**
	 * 接收用户输入 并判断是否是正确的数字
	 * 
	 * @return
	 */
	public static int input() {
		input = new Scanner(System.in);
		String s = input.nextLine();
		if (!isNumberStr(s)) {
			System.err.println("输入正确的整数");
			return input();
		} else {
			return Integer.parseInt(s);
		}
	}

	/**
	 * 验证字符串能不能转数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumberStr(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
