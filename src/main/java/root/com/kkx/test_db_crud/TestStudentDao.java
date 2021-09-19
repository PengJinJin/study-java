package root.com.kkx.test_db_crud;


import org.testng.annotations.Test;

import java.util.List;

public class TestStudentDao {

	@Test
	public void test() {
		Student student = new Student(3, "圈圈好呀2", 2);
		StudentDao dao = new StudentDao();
		System.out.println(dao.addStudent(student));
	}

	@Test
	public void test2() {
		Student student = new Student(1, "圈圈好呀2", 1);
		StudentDao dao = new StudentDao();
		System.out.println(dao.updateStudent(student));
	}

	@Test
	public void test3() {
		StudentDao dao = new StudentDao();
		System.out.println(dao.deleteStudent(1));
	}

	@Test
	public void test4() {
		StudentDao dao = new StudentDao();
		Student byId = dao.findById(3);
		System.out.println(byId);
	}

	@Test
	public void test5() {
		StudentDao dao = new StudentDao();
		List<Student> list = dao.findAll();
		list.size();
		System.out.println(list);
	}

}
