package com.kkx.day04;

import java.io.*;

public class CharStreamDemo {
	public static void main(String[] args) throws IOException {
		try {
			PrintWriter p = new PrintWriter("a.txt");
			PrintWriter p1 = new PrintWriter("a.txt");

			FileOutputStream fos = new FileOutputStream("a.txt", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			PrintWriter p2 = new PrintWriter(osw);


			p.println("法西斯1");
//			p.write("\n");
			p.println("法西斯2");
			p.close();
			p1.println("法西斯3");
			p1.println("法西斯4");
			p1.close();

			p2.println("zhuzhu5");
			p2.println("qq 6");
			p2.close();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("a.txt"))
			);
			String str;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
			br.close();
		} catch (IOException e) {
		}

	}

}

