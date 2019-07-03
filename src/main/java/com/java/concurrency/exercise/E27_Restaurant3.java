package com.java.concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.java.util.Print.print;
import static com.java.util.Print.printnb;

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
					// 循环，如果餐是空的就一只等待
					while (restaurant3.meal == null) {
						con.await();
					}
				} finally {
					lock.unlock();
				}
				print("服务员取到了" + restaurant3.meal);
				// 给厨师加锁
				restaurant3.chef3.lock.lock();
				try {
					// 唤醒所有厨师，告诉他们该干活了
					restaurant3.meal = null;
					restaurant3.chef3.con.signalAll();
				} finally {
					// 解锁之后就立马执行chef3的方法了
					restaurant3.chef3.lock.unlock();
				}
			}
		} catch (Exception e) {
			print("服务员 断开连接");
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
					print("今天的卖完了，关门");
					restaurant3.exec.shutdownNow();
				}
				printnb("上菜咯! ");
				// 先给服务员加锁
				restaurant3.person3.lock.lock();
				try {
					// 叫起来服务员，该干活了
					restaurant3.meal = new Meal2(count);
					restaurant3.person3.con.signalAll();
				} finally {
					// 解锁之后就回执行 person3 的方法
					restaurant3.person3.lock.unlock();
				}
				TimeUnit.MILLISECONDS.sleep(1000);
			}
		} catch (Exception e){
			print("厨师 断开连接");
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
