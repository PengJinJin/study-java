package com.java.io;

import java.io.*;
import java.util.Date;

import static com.java.util.Print.print;

/**
 * 主要是transient关键字
 * 应用场景为 有些东西不想被序列化,比如登录校验完成,想保存数据,而不想保存密码
 */
public class Logon implements Serializable {

	private Date date = new Date();
	private String username;
	private transient String password;

	public Logon(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/*
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeObject(password);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		password = (String) in.readObject();
	}
	*/

	@Override
	public String toString() {
		return "logon info: \nusername: " + username + ",\ndate: " + date + ",\npassword: " + password;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Logon logon = new Logon("Ethan", "w0sh1N188");
		print(logon);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Logon.out"))) {
			oos.writeObject(logon);
		}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Logon.out"))) {
			print("Recovering object at: " + new Date());
			logon = (Logon) ois.readObject();
		}
		print(logon);
	}
}
