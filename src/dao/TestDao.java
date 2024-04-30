package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao  extends Dao{

	public Test get(Student student, Subject subject, School school, int no)throws Exception{
		//学生インスタンスを初期化
				Test test = new Test();
				//データベースへのコネクションを確立
				Connection connection = getConnection();

				//プリペアードステートメント
				PreparedStatement statement = null;
				try{
					//プリペアードステートステート面とにSQL文をセット
					statement = connection.prepareStatement("select * from student where student_no=? ");
					//プリペアードステートメントに学校コードをバインド
					statement.setString(1, test);
					//プリペアードステートメントを実行
					ResultSet rSet = statement.executeQuery();
					//学校Daoを初期化
					SchoolDao schoolDao = new SchoolDao();

		return null;

	}
	private List<Test> postFilter(ResultSet rSet , School school)throws Exception{
		return null;

	}
	private List<Test> filter(int entYear, String classNum, Subject subject, int num, School school)throws Exception{
		return null;

	}
	public boolean save(List<Test> list)throws Exception{



		return false;

	}
	public boolean save(Test test, Connection connection)throws Exception{



		return false;
	}
	public boolean delete(List<Test> list)throws Exception{



		return false;
	}
public boolean delete(Test test, Connection connection)throws Exception{



		return false;

	}
}
