package com.java.enumerated;

import java.util.EnumMap;
import java.util.Map;

import static com.java.enumerated.AlarmPoints.*;
import static com.java.util.Print.print;
import static com.java.util.Print.printnb;

/**
 * EnumMap是一种特殊的Map, key只能是Enum<br/>
 * 匿名内部类方式<br/>
 * {@link CarWash}
 */
public class EnumMaps {

	public static void main(String[] args) {
		EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
		em.put(KITCHEN, () -> print("Kitchen fire!"));
		em.put(BATHROOM, () -> print("Bathroom alert!"));

		for (Map.Entry<AlarmPoints, Command> e : em.entrySet()) {
			printnb(e.getKey() + ": ");
			e.getValue().action();
		}

		try {
			// 如果没有存入value却调用,是会抛异常的
			em.get(UTILITY).action();
		} catch (Exception e) {
			print(e);
		}
	}

}

interface Command {
	void action();
}
