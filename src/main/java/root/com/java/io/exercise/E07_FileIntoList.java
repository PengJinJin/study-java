package root.com.java.io.exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 打开一个文本文件,每次读取一行内容
 * 将每行作为一个String读入,并将对象置入一个LinkedList中,按照相反的顺序打印出来
 */
public class E07_FileIntoList {

	public static List<String> read(String filename) throws IOException {
		try (
				BufferedReader in = new BufferedReader(new FileReader(filename))
		) {
			String s;
			List<String> list = new LinkedList<>();
			while ((s = in.readLine()) != null) {
				list.add(s);
			}
			return list;
		}
	}

	public static void main(String[] args) throws IOException {
		String path = "D:\\eclipse-workspace\\MyWebSocket\\src\\com\\java\\io\\exercise\\E07_FileIntoList.java";
		List<String> list = read(path);
		// hasPrevious():如果以逆向遍历列表，列表迭代器前面还有元素，则返回 true，否则返回false
		for (ListIterator<String> it = list.listIterator(list.size()); it.hasPrevious(); ) {
			System.out.println(it.previous().toUpperCase());
		}
	}

}
