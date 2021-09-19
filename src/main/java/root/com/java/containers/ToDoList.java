package root.com.java.containers;

import java.util.PriorityQueue;

/**
 * 优先级示例,是通过实现Comparable控制的
 */
class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {

	static class ToDoItem implements Comparable<ToDoItem> {

		private char primary;
		private int secondary;
		private String item;

		public ToDoItem(char primary, int secondary, String item) {
			this.primary = primary;
			this.secondary = secondary;
			this.item = item;
		}

		@Override
		public int compareTo(ToDoItem o) {
			// 当前对象比比较对象大，返回正数，相等返回0，当前对象小于比较对象返回负数
			if (primary > o.primary) {
				return +1;
			}
			if (primary == o.primary) {
				if (secondary > o.secondary) {
					return +1;
				} else if (secondary == o.secondary) {
					return 0;
				}
			}
			return -1;
		}

		@Override
		public String toString() {
			return Character.toString(primary) + secondary + ": " + item;
		}
	}

	public void add(String td, char pri, int sec) {
		super.add(new ToDoItem(pri, sec, td));
	}

	public static void main(String[] args) {
		ToDoList toDoList = new ToDoList();
		toDoList.add("Empty trash", 'C', 4);
		toDoList.add("Feed dog", 'A', 2);
		toDoList.add("Feed bird", 'B', 7);
		toDoList.add("Mow lawn", 'C', 3);
		toDoList.add("Water lawn", 'A', 1);
		toDoList.add("Feed cat", 'B', 1);
		while (!toDoList.isEmpty()) {
			System.out.println(toDoList.remove());
		}
	}

}
