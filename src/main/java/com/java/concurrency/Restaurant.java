package com.java.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.java.util.Print.print;
import static com.java.util.Print.printnb;

class Meal {
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public String toString() {
		return "Meal " + orderNum;
	}
}

class WaitPerson implements Runnable {

	private Restaurant restaurant;

	public WaitPerson(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null) {
						wait();// shutdownNow后，调用sleep或是wait或是使线程阻塞的方法都会异常
					}
				}
				print("WaitPerson got" + restaurant.meal);
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (InterruptedException e) {
			print("WaitPerson interrupted");
//			e.printStackTrace();
		}
	}
}

class Chef implements Runnable {

	private Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					// 如果meal不为空,等待用完
					while (restaurant.meal != null) {
						wait();
					}
				}
				if (++count == 10) {
					print("Out of food, closing");
					restaurant.exec.shutdownNow();
					// 练习25，直接return查看打印变化，return之后，表示Chef任务直接退出了
//					return;
				}
				printnb("Order up" + count + "! ");
				synchronized (restaurant.person) {
					restaurant.meal = new Meal(count);
					restaurant.person.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (Exception e) {
			print("Chef interrupted");
		}
	}
}

public class Restaurant {

	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson person = new WaitPerson(this);
	Chef chef = new Chef(this);

	public Restaurant() {
		exec.execute(chef);
		exec.execute(person);
	}

	public static void main(String[] args) {
		new Restaurant();
	}
}
