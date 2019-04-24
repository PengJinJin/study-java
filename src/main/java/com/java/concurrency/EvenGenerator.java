package com.java.concurrency;

public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	@Override
	public int next() {
		++currentEvenValue;// ����û��ִ�о͵�����next()
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
