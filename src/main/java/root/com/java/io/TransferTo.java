package root.com.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferTo {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("arguments: sourcefile destfile");
			System.exit(1);
		}

		try (
				FileChannel in = new FileInputStream(args[0]).getChannel();
				FileChannel out = new FileOutputStream(args[1]).getChannel()
		) {
			in.transferTo(0, in.size(), out);
			// or
			// out.transferFrom(in, 0, in.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
