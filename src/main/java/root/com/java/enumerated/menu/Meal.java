package root.com.java.enumerated.menu;

public class Meal {

	public static void main(String[] args) {
		// 5个菜单，每个食物一种，随机的
		for (int i = 0; i < 5; i++) {
			for (Course course : Course.values()) {
				Food food = course.randomSelection();
				System.out.println(food);
			}
			System.out.println("---");
		}
	}

}
