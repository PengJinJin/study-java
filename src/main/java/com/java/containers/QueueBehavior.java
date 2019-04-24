package com.java.containers;

import com.java.util.Generator;
import com.java.util.Print;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import static com.java.util.Print.printnb;
import static com.java.util.Print.print;

public class QueueBehavior {

	private static int count = 10;

	// remove、element、offer 、poll、peek 属于Queue接口。
	static <T> void test(Queue<T> queue, Generator<T> gen) {
		for (int i = 0; i < count; i++) {
			// offer：添加一个元素并返回true。如果队列已满，则返回false
			queue.offer(gen.next());
		}
		// peek：返回队列头部的元素。如果队列为空，则返回null
		while (queue.peek() != null) {
			// remove：移除并返回队列头部的元素。如果队列为空，则抛出一个NoSuchElementException异常
			printnb(queue.remove() + " ");
		}
		Print.print();
	}

	static class Gen implements Generator<String> {

		String[] s = ("one two three four five six seven eight nine ten").split(" ");

		int i;

		@Override
		public String next() {
			return s[i++];
		}

	}


	/**
	 * Queue仅有的两个实现是LinkedList和PriorityQueue
	 * 他们的差异在于排序行为而不是性能
	 * @see com.java.containers.ToDoList.ToDoItem
	 */
	public static void main(String[] args) {
		test(new LinkedList<>(), new Gen());
		/**
		 * {@link com.java.containers.ToDoList.ToDoItem}
		 */
		test(new PriorityQueue<>(), new Gen());// 是一个有序列表，元素根据天然排序（通过其 java.util.Comparable 实现），或者根据传递给构造函数的java.util.Comparator 实现来定位
		test(new ArrayBlockingQueue<>(count), new Gen());// 需要指定容量,FIFO(first in first out)排序
		test(new ConcurrentLinkedQueue<>(), new Gen());
		test(new LinkedBlockingQueue<>(), new Gen());// 不指定时容量为Integer.MAX_VALUE,FIFO(first in first out)排序
		test(new PriorityBlockingQueue<>(), new Gen());// 一个带优先级的队列,但不是FIFO队列
	}

}
