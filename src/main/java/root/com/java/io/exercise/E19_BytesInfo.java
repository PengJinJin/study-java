package root.com.java.io.exercise;

import root.com.java.io.BinaryFile;

import java.io.IOException;
import java.util.*;

public class E19_BytesInfo {
	public static void main(String[] args) throws IOException {
		Map<Byte, Integer> bytesStat = new HashMap<>();
		for (Byte bt : BinaryFile.read("E19_BytesInfo.class")) {
			Integer freq = bytesStat.get(bt);
			bytesStat.put(bt, freq == null ? 1 : freq + 1);
		}
		List<Byte> keys = new ArrayList<>(bytesStat.keySet());
		Collections.sort(keys);
		for (Byte key : keys)
			System.out.println(key + " => " + bytesStat.get(key));

	}
}
