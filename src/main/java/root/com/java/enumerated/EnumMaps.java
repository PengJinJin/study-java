package root.com.java.enumerated;

import root.com.java.util.Print;

import java.util.EnumMap;
import java.util.Map;

import static root.com.java.util.Print.print;

/**
 * EnumMap是一种特殊的Map, key只能是Enum<br/>
 * 匿名内部类方式<br/>
 * {@link CarWash}
 */
public class EnumMaps {

	public static void main(String[] args) {
		EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
		em.put(AlarmPoints.KITCHEN, () -> Print.print("Kitchen fire!"));
		em.put(AlarmPoints.BATHROOM, () -> Print.print("Bathroom alert!"));

		for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
			Print.printnb(e.getKey() + ": ");
			e.getValue().action();
		}

		try {
			// 如果没有存入value却调用,是会抛异常的
			em.get(AlarmPoints.UTILITY).action();
		} catch (Exception e) {
			Print.print(e);
		}
	}

}

interface Command {
	void action();
}
