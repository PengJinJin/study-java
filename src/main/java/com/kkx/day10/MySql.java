package com.kkx.day10;

import com.kkx.util.DBUtil_Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySql {
	public static void main(String[] args) throws Exception {
		//加载驱动
//		Class.forName("com.mysql.cj.jdbc.Driver");
		//连接数据库
//		String url = "jdbc:mysql://localhost:3306/kkx-study?useUnicode=true&serverTimezone=GMT&characterEncoding=UTF8";
//		String userName = "root";
//		String passWord = "root";
//		Connection connection = DriverManager.getConnection(url, userName, passWord);

		Connection  connection= DBUtil_Mysql.getConnection();


		//编写sql语句
		String sql = "select * from student where id = 1";
		//获取statement对象
		Statement statement = connection.createStatement();
		//执行sql
		ResultSet resultSet = statement.executeQuery(sql);
		//处理结果集（查询）
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
//			System.out.println(resultSet.getString(2));
			System.out.println(resultSet.getInt("age"));
		}

	  DBUtil_Mysql.closed(connection);
		//关闭连接
//		resultSet.close();
//		statement.close();
//		connection.close();
	}
}
