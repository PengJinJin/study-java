package kkx.test;

public class Test0 {
	public static void main(String[] args) {
		char z = 'A';
		System.out.println((int)z);
		String a = "TestService";
		char c = a.charAt(0);
		System.out.println(c);
		char h = (char) (c + 32);
		System.out.println(h);
		System.out.println(h + a.substring(1));
	}
}
