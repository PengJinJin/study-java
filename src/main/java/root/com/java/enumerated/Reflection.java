package root.com.java.enumerated;

import root.com.java.io.OSExecute;
import root.com.java.util.Print;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import static root.com.java.util.Print.print;

enum Explore {
	HERE, THERE
}

public class Reflection {

	public static Set<String> analyze(Class<?> enumClass) {
		Print.print("----- Analyzing " + enumClass + " -----");
		Print.print("Interfaces:");
		for (Type t : enumClass.getGenericInterfaces()) {
			Print.print(t);
		}
		Print.print("Base: " + enumClass.getSuperclass());
		Print.print("Methods: ");
		Set<String> methods = new TreeSet<>();
		for (Method m : enumClass.getMethods()) {
			methods.add(m.getName());
		}
		Print.print(methods);
		return methods;
	}

	public static void main(String[] args) {
		String classFile = "D:/eclipse-workspace/MyWebSocket/build/classes/com/java/enumerated/Explore";
		Set<String> exploreMethods = analyze(Explore.class);
		Set<String> enumMethods = analyze(Enum.class);

		Print.print("Explore.containsAll(Enum)? "
				+ exploreMethods.containsAll(enumMethods));
		Print.printnb("Explore.removeAll(Enum): ");
		exploreMethods.removeAll(enumMethods);
		Print.print(exploreMethods);

		OSExecute.command("javap "+classFile);
	}

}
