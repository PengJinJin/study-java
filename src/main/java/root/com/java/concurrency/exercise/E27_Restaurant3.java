package root.com.java.concurrency.exercise;

import root.com.java.util.Print;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static root.com.java.util.Print.print;

class WaitPerson3 implements Runnable {

	Restaurant3 restaurant3;

	Lock lock = new ReentrantLock();
	Condition con = lock.newCondition();

	public WaitPerson3(Restaurant3 restaurant3) {
		this.restaurant3 = restaurant3;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				lock.lock();
				try {
					// ѭ����������ǿյľ�һֻ�ȴ�
					while (restaurant3.meal == null) {
						con.await();
					}
				} finally {
					lock.unlock();
				}
				Print.print("����Աȡ����" + restaurant3.meal);
				// ����ʦ����
				restaurant3.chef3.lock.lock();
				try {
					// �������г�ʦ���������Ǹøɻ���
					restaurant3.meal = null;
					restaurant3.chef3.con.signalAll();
				} finally {
					// ����֮�������ִ��chef3�ķ�����
					restaurant3.chef3.lock.unlock();
				}
			}
		} catch (Exception e) {
			Print.print("����Ա �Ͽ�����");
		}
	}
}

class Chef3 implements Runnable {
	Restaurant3 restaurant3;
	Lock lock = new ReentrantLock();
	Condition con = lock.newCondition();
	private int count;

	public Chef3(Restaurant3 restaurant3) {
		this.restaurant3 = restaurant3;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				lock.lock();
				try {
					while (restaurant3.meal != null) {
						con.await();
					}
				} finally {
					lock.unlock();
				}
				if (++count == 10) {
					Print.print("����������ˣ�����");
					restaurant3.exec.shutdownNow();
				}
				Print.printnb("�ϲ˿�! ");
				// �ȸ�����Ա����
				restaurant3.person3.lock.lock();
				try {
					// ����������Ա���øɻ���
					restaurant3.meal = new Meal2(count);
					restaurant3.person3.con.signalAll();
				} finally {
					// ����֮��ͻ�ִ�� person3 �ķ���
					restaurant3.person3.lock.unlock();
				}
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		} catch (Exception e){
			Print.print("��ʦ �Ͽ�����");
		}
	}
}

class Restaurant3 {
	Meal2 meal;
	ExecutorService exec = Executors.newCachedThreadPool();

	WaitPerson3 person3 = new WaitPerson3(this);
	Chef3 chef3 = new Chef3(this);

	public Restaurant3() {
		exec.execute(chef3);
		exec.execute(person3);
	}
}


public class E27_Restaurant3 {
	public static void main(String[] args) {
		new Restaurant3();
	}
}
