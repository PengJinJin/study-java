package root.com.java.containers;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static root.com.java.util.Print.print;

public class SpringDetector {

	public static <T extends GroundHog> void detectSpring(Class<T> type) throws Exception {
		Constructor<T> ghog = type.getConstructor(int.class);// 获取对应的有参构造函数
		Map<GroundHog, Prediction> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			map.put(ghog.newInstance(i), new Prediction());
		}
		print("map=" + map);

		GroundHog hog = ghog.newInstance(3);
		print("Look up prediction for " + hog);

		if (map.containsKey(hog)) {
			print(map.get(hog));
		} else {
			// 始终会走else,因为判断是否存在,需要重写equals和hashCode
			// 在使用HashSet作为key的时候，必须要注意
			print("Key not found: " + hog);
		}

	}

	/**
	 * {@link GroundHog2}
	 * {@link SpringDetector2}
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		detectSpring(GroundHog.class);
	}

}
