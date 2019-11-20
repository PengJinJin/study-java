package com.kkx.day10;

import com.kkx.test_db_crud.Student;
import com.kkx.util.DBUtil_Mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestPrepared {
	public static void main(String[] args) throws SQLException {
		Connection connection = DBUtil_Mysql.getConnection();
		//getMetaData 获取元数据
		DatabaseMetaData metaData = connection.getMetaData();
		System.out.println(metaData.getURL());
		System.out.println(metaData.getUserName());
		String name = "qq";
		int age = 20;
//		String sql="select * from student where s_name='"+name+"' and age="+age;
//		Statement statement = connection.createStatement(sql);
		String sql = "select * from student where s_name=? and age=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSetMetaData metaData1 = ps.getMetaData();
		System.out.println(metaData1.getColumnCount());
		ps.setString(1, name);
		ps.setInt(2, age);
		ResultSet resultSet = ps.executeQuery();

		List<Student> students = new ArrayList<>();
		while (resultSet.next()) {
			System.out.println(resultSet.getString(2) + ";" + resultSet.getInt(3));
			Student s = new Student(resultSet.getInt(1),
							resultSet.getString(2),
							resultSet.getInt(3));
			students.add(s);
		}
		DBUtil_Mysql.closed(connection);

	}

}
