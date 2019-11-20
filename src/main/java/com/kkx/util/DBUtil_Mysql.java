package com.kkx.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil_Mysql {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	static {
		try {
			//创建Properties对象
			Properties prop = new Properties();
			//加载properties文件
			prop.load(DBUtil_Mysql.class.getClassLoader().getResourceAsStream("db_mysql.properties"));
			//获取文件中的值
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			Class.forName(driver);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接数据库
	 */
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭数据库连接
	 * @param connection
	 */
	public static void closed(Connection connection){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
