package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
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
					statement.setString(1, student.getStudent_no());
					statement.setString(2, subject.getSubject_cd());
					statement.setString(3, school.getSchool_cd());
					statement.setInt(4, no);
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

		private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
			List<Test> list = new ArrayList<>();
			try {
				while (rSet.next()) {
					Test test = new Test();
					StudentDao  studentDao= new StudentDao();
					SubjectDao  subjectDao= new SubjectDao();

					test.setStudent(studentDao.get(rSet.getString("student_no")));
					test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
					test.setPoint(rSet.getInt("point"));
					test.setNo(rSet.getInt("test.no"));
					test.setClassNum(rSet.getString("class_num"));
					test.setSchool(school);
					list.add(test);
				}
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
			return list;
		}
	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school)throws Exception{
		List<Test> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文の条件
		String condition = "where ent_year =? and student.class_num=? and"
				+ "(subject_cd =? or subject_cd is null) and "
			+	"(test.no =? or test.no is null) and student.School_cd =?";
		//SQL文のソート
		String order =" order by no asc";

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from student left outer join test on student. no =test.student_no"
					+ condition + order);
			//プリペアードステートメントに入学年度をバインド
			statement. setInt (1, entYear) ;
			//プリペアードステートメントにクラス番号をバインド
			statement. setString(2, classNum);
			//プリペアードステートメントに科目コードをバインド
			statement. setString(3, subject.getSubject_cd());
			statement. setInt(4, num);
			statement. setString(5, school. getSchool_cd ());


			//プライベートステートメントを実行
			rSet = statement. executeQuery ();
			//帰ってきたResultSet型をtest型に変えて結果をセットする
			list =postFilter(rSet,school);
		}catch (Exception e) {
			throw e;
		}finally {
			if(statement != null) {
				try{
					statement. close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if(connection != null){
			try{
				connection.close();
			}catch (SQLException sqle) {
				throw sqle;
			}
		}
		return list;

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
