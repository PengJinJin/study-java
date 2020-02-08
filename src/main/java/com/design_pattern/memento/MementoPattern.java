package com.design_pattern.memento;

import java.util.ArrayList;
import java.util.List;

public class MementoPattern {

	public static void main(String[] args) {
		Originator originator = new Originator();
		Caretaker caretaker = new Caretaker();
		originator.setState("State #1");
		originator.setState("State #2");
		caretaker.add(originator.saveStateToMemento());

		originator.setState("State #3");
		caretaker.add(originator.saveStateToMemento());

		originator.setState("State #4");

		System.out.println("Current State: " + originator.getState());

		// 可以选择恢复到什么时候到状态
		originator.restoreStateFromMemento(caretaker.get(0));
		System.out.println("Restore First Save State: " + originator.getState());

		originator.restoreStateFromMemento(caretaker.get(1));
		System.out.println("Restore Second Save State: " + originator.getState());

		System.out.println("All: ");
		caretaker.out();
	}
}

// 备忘录角色
class Memento {
	private String state;

	public Memento(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return getClass().getName() + ": " + state;
	}
}

// 发起人角色
class Originator {
	private String state;

	// 保存当前状态
	public Memento saveStateToMemento() {
		return new Memento(state);
	}

	// 恢复状态
	public void restoreStateFromMemento(Memento m) {
		state = m.getState();
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}

// 管理者角色
class Caretaker {

	private List<Memento> list = new ArrayList<>();

	public void add(Memento m) {
		list.add(m);
	}

	public Memento get(int index) {
		return list.get(index);
	}

	public void out() {
		list.forEach(System.out::println);
	}

}