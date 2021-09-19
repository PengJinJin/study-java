package root.com.kkx.test_db_crud;

import root.com.kkx.util.DBUtil_Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

	/**
	 * addStudent sql
	 *
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		Connection connection = null;
		try {
			connection = DBUtil_Mysql.getConnection();
			String sql = "insert into student values(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getAge());
			int i = ps.executeUpdate();
			return i > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil_Mysql.closed(connection);
		}
		return false;
	}

	/**
	 * updateStudent sql
	 *
	 * @param student
	 * @return
	 */
	public boolean updateStudent(Student student) {
		Connection con = null;
		try {
			con = DBUtil_Mysql.getConnection();
			String sql = "update student set s_name=?,age=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getAge());
			ps.setInt(3, student.getId());
			int i = ps.executeUpdate();
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil_Mysql.closed(con);
		}
		return false;
	}

	/**
	 * deleteStudent sql
	 *
	 * @param id
	 * @return
	 */
	public boolean deleteStudent(int id) {
		Connection con = null;
		try {
			con = DBUtil_Mysql.getConnection();
			String sql = "delete from student where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil_Mysql.closed(con);
		}
		return false;
	}

	/**
	 * findById sql
	 *
	 * @param id
	 * @return
	 */
	public Student findById(int id) {
		Connection con=null;
		try {
			 con = DBUtil_Mysql.getConnection();
			String sql = "select * from student where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setAge(resultSet.getInt("age"));
				student.setName(resultSet.getString("s_name"));
				return student;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil_Mysql.closed(con);
		}
		return null;//表示没有查到
	}

	/**
	 * findAll sql
	 * @return
	 */

	public List<Student> findAll(){
		Connection con=null;
		try {
			con = DBUtil_Mysql.getConnection();
			String sql = "select * from student";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			List<Student> list = new ArrayList<>();

			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setAge(resultSet.getInt("age"));
				student.setName(resultSet.getString("s_name"));
				list.add(student);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil_Mysql.closed(con);
		}
		return null;//表示没有查到
	}

}
