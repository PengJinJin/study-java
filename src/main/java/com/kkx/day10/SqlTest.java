package com.kkx.day10;

import com.kkx.util.DBUtil_Mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlTest {

	public static void main(String[] args) throws SQLException {
		Connection connection= DBUtil_Mysql.getConnection();
		String sql = "insert into student values(2,'大叔',3)";
		Statement statement=connection.createStatement();
		int i = statement.executeUpdate(sql);
		System.out.println(i);
    DBUtil_Mysql.closed(connection);

	}




}
