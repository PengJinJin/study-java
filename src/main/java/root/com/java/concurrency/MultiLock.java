package root.com.java.concurrency;

import root.com.java.util.Print;

import static root.com.java.util.Print.print;

/**
 * 对象锁
 * f1()方法可以调用同一个对象的其他synchronized方法f2(),因为f1()已经获取对象锁了
 */
public class MultiLock {

	public synchronized void f1(int count) {
		if (count-- > 0) {
			Print.print("f1() calling f2() with count" + count);
			f2(count);
		}
	}

	public synchronized void f2(int count) {
		if (count-- > 0) {
			Print.print("f2() calling f1() with count" + count);
			f1(count);
		}
	}

	public static void main(String[] args) {
		final MultiLock lock = new MultiLock();
		new Thread(() -> lock.f2(10)).start();
	}

}
