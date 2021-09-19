package root.com.java.containers;

import root.com.java.util.CountingMapData;
import root.com.java.util.Print;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static root.com.java.util.Print.printnb;
import static root.com.java.util.Print.print;

public class Maps {

	public static void printKeys(Map<Integer, String> map) {
		printnb("Size = " + map.size() + ". ");
		printnb("Keys = " + map.keySet());
		Print.print();
	}

	public static void test(Map<Integer, String> map) {
		print(map.getClass().getSimpleName());
		map.putAll(new CountingMapData(25));
		// Map has 'Set' behavior for keys:
		map.putAll(new CountingMapData(25));
		printKeys(map);
		// Producing a Collection of the values:
		print(map.values());
		print(map);
		print("map.containsKey(11): " + map.containsKey(11));
		print("map.get(11): " + map.get(11));
		print("map.containsValue(\"F0\"): " + map.containsValue("F0"));
		Integer key = map.keySet().iterator().next();
		print("First key in map: " + key);
		map.remove(key);
		printKeys(map);
		map.clear();
		print("map.isEmpty(): " + map.isEmpty());
		map.putAll(new CountingMapData(25));
		// Operations on the Set change the Map:
		map.keySet().removeAll(map.keySet());
		print("map.isEmpty(): " + map.isEmpty());
	}

	public static void main(String[] args) {
		test(new HashMap<>());
		test(new TreeMap<>());
		test(new LinkedHashMap<>());
		test(new IdentityHashMap<>());
		test(new ConcurrentHashMap<>());
		test(new WeakHashMap<>());
	}

}
