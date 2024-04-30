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
					statement = connection.prepareStatement("select * from test where student_no=? and subject_cd=? and no");
					statement.setString(1, student.getNo());
					statement.setString(2, subject.getCd());
					statement.setString(3, school.getCd());
					statement.setInt(4, no());
					ResultSet rSet = statement.executeQuery();

					if (rSet.next()) {
						test.setStudent(student);
						test.setSubject(subject);
						test.setSchool(school);
						test.setNo(no);
						test.setPoint(rSet.getInt("point"));
					}else{
						test = null;
					}
				}catch (Exception e){
					throw e;
				}finally{
					if (statement !=null){

					}
				}
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
