package com.kkx.test_db_crud;

import com.kkx.util.DBUtil_Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 批处理：快速造数据
 */
public class TestBatch {
	public static void main(String[] args) {
		Connection con = DBUtil_Mysql.getConnection();
		String sql = "insert into student values(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int count = 10;
			int size = 4;
			for (int i = 1; i <= count; i++) {
				ps.setInt(1, i);
				ps.setString(2, "qq" + i);
				ps.setInt(3, 5 + i);
				ps.addBatch();
				if (i % size == 0) {
					System.out.println(i);
					Thread.sleep(5000);
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			ps.executeBatch();
			ps.clearBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil_Mysql.closed(con);
		}

	}
}
