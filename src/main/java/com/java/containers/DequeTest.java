package com.java.containers;

import com.java.util.Deque;
import com.java.util.Print;

import static com.java.util.Print.printnb;
import static com.java.util.Print.print;

public class DequeTest {

	static void fillTest(Deque<Integer> deque) {
		for (int i = 20; i < 27; i++) {
			deque.addFirst(i);
		}
		for (int i = 50; i < 55; i++) {
			deque.addLast(i);
		}
	}

	public static void main(String[] args) {
		Deque<Integer> di = new Deque<>();
		fillTest(di);
		print(di);
		//
		while (di.size() > 0) {
			printnb(di.removeFirst() + " ");
		}
		Print.print();
		fillTest(di);
		while (di.size() > 0) {
			printnb(di.removeLast() + " ");
		}
	}

}
