package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{

	/**
	 * baseSql:String 共通SQL文　プライベート
	 */

	private String baseSql = "select subject.name,test.subject_cd,test.NO,test.POINT from test left outer join subject on test.subject_cd = subject.Subject_cd ";


	/**
	 *  postFilterメソッド　フィルター後のリストへの格納処理
	 *
	 *  @param rSet:リザルトセット
	 *  @return List<TestListStudent>学生別成績リスト 存在しない場合は0件のリスト
	 *  @throws Exception
	 */
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception{
		List<TestListStudent> list = new ArrayList<>();

		/*try{
			//リザルトセットを全件走査
			while(rSet.next()){
				//学生別成績インスタンスを初期化
				TestListStudent test = new TestListStudent();

				//学生別成績インスタンスに検索結果をセット
				test.setSubjectName(rSet.getString("subject_name"));
				test.setSubjectCd(rSet.getString("subject_cd"));
				test.setPoint(rSet.getInt("point"));
				test.setNum(rSet.getInt("no"));

				//リストに追加
				list.add(test);*/

		try {
		    while (rSet.next()) {
		        TestListStudent test = new TestListStudent();

		        // null チェックを追加
		        if (rSet.getString("name") != null) {
		            test.setSubjectName(rSet.getString("name"));
		        }
		        if (rSet.getString("subject_cd") != null) {
		            test.setSubjectCd(rSet.getString("subject_cd"));
		        }
		        if (!rSet.wasNull()) {
		            test.setPoint(rSet.getInt("point"));
		        }
		        if (!rSet.wasNull()) {
		            test.setNum(rSet.getInt("no"));
		        }

		        list.add(test);
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return list;
	}


	/**
	 * filterメソッド 学生を指定して学生別成績一覧の取得
	 *
	 * @param student:Student 学生
	 *
	 * @return List<TestListStudent> 学生別成績リスト　存在しない場合は0件
	 * @throws Exception
	 */
	public List<TestListStudent> filter(Student student,School school)throws Exception{
		//リストを初期化
		List<TestListStudent> list =new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文の条件
		String condition = "and subject.school_cd =? where test.student_no =?";

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql+condition);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getSchool_cd());
			//プリペアードステートメントに学生番号をバインド
			statement.setString(2, student.getStudent_no());
			//プリペアードステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet);


		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステート面とをとじる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		return list;


	}

}
