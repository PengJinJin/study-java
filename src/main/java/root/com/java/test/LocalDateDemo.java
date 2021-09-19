package root.com.java.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class LocalDateDemo {

	public static void main(String[] args) {
		test1();
	}

	/**
	 * ʹ�ù���������ϵͳʱ���л�ȡ��ǰ������
	 */
	private static void test1() {
		LocalDate today = LocalDate.now();
		System.out.println(today);// ����2018-03-20
		int year = today.getYear(); // ����2018
		Month month = today.getMonth(); // ����MARCH
		int day = today.getDayOfMonth(); // ����20
		DayOfWeek dow = today.getDayOfWeek(); // ����TUESDAY
		int len = today.lengthOfMonth(); // ����31 (days in March)
		boolean leap = today.isLeapYear(); // ����false (not a leap year)
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(dow);
		System.out.println(len);
		System.out.println(leap);
	}

}
