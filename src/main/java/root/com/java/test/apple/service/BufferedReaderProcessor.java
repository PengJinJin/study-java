package root.com.java.test.apple.service;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {

	/**
	 * ����BufferedReader�ӿ�
	 * 
	 * @param b
	 * @return
	 * @throws IOException
	 */
	String process(BufferedReader b) throws IOException;

}
