package com.design_pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 使用JDK的Observer和Observable实现观察者模式
 */
public class JDKObserverDemo {
	public static void main(String[] args) {
		JSubject subject = new JSubject();
		new BinaryObserver(subject);
		new OctalObserver(subject);
		new HexObserver(subject);

		System.out.println("First state change: 15");
		subject.setState(15);
		System.out.println("Second state change: 10");
		subject.setState(10);

	}

}

class JSubject extends Observable {
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		setChanged();
		notifyObservers();
	}
}

class BinaryObserver implements Observer {

	public BinaryObserver(Observable observable) {
		observable.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Binary String: "
						+ Integer.toBinaryString(((JSubject) o).getState()));
	}
}

class HexObserver implements Observer {

	public HexObserver(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Hex String: "
						+ Integer.toHexString(((JSubject) o).getState()));
	}
}

class OctalObserver implements Observer {
	public OctalObserver(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Octal String: "
						+ Integer.toOctalString(((JSubject) o).getState()));
	}
}