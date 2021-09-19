package root.com.kkx.day05;

import java.util.Arrays;

public class Ex09 {
	char[] ch = {'a', 'b', 'c'};
	String str="good";
	public static void change (String s,char[] ch){
		ch[0]='g';
		s="test ok";
	}
	public static void main(String[] args){
		Ex09 e=new Ex09();
		change(e.str,e.ch);
		System.out.println(e.str + " and " + Arrays.toString(e.ch));
	}

}
