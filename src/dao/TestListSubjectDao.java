package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{

	/**
	 * baseSql:String 共通SQL文　プライベート
	 */

	private String baseSql = "select distinct student.ent_year,test.CLASS_NUM,TEST.STUDENT_NO,STUDENT.NAME,TEST.NO,test.POINT from test join SUBJECT  on test.SUBJECT_CD = subject.SUBJECT_CD join STUDENT  on test.STUDENT_NO = student.STUDENT_NO  ";


	/**
	 * postFilterメソッド　フィルター後のリストへの格納処理
	 *
	 * @param rSet:リザルトセット
	 *
	 * @return 科目別の学生の成績リスト:List<TestListSubject>存在しない場合は0件のリスト
	 * @throws Exception
	 */
	private List<TestListSubject> postFilter(ResultSet rSet)throws Exception{

		List<TestListSubject> list = new ArrayList<>();
		String student = "";
		TestListSubject test = new TestListSubject();
		Map<Integer, Integer> points = new HashMap<>();


		try {
		    while (rSet.next()) {

		    	//前回と同じ学生が連続した場合
		    	if(student.equals(rSet.getString("student_no"))){

		    		//学生インスタンスに検索結果をセット
		    		points.put(rSet.getInt("no"), rSet.getInt("point"));
		    		test.setPoints(points);

		    	//前回と違う学生が来た場合
		    	}else{

		    		if (!(student.equals(""))) {
						//リストに追加
		    			list.add(test);
					}

		    	test = new TestListSubject();
		    	points = new HashMap<>();


		        // null チェックを追加
		        test.setEntYear(rSet.getInt("ent_year"));
		        test.setStudentNo(rSet.getString("student_no"));
		        test.setStudentName(rSet.getString("name"));
		        test.setClassNum(rSet.getString("class_num"));
		        points.put(rSet.getInt("no"), rSet.getInt("point"));
		        test.setPoints(points);

		        //学生情報を更新
		        student = rSet.getString("student_no");

		    	}
		    }

		    if (!(student.equals(""))) {
				//リストに追加
    			list.add(test);
			}

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return list;

	}

	/**
	 * filterメソッド　入学年度、クラス番号、科目、学校を指定して科目別成績一覧を取得する
	 *
	 * @param entYear:int 入学年度
	 * @param classNum:String クラス番号
	 * @param subject:Subject 科目
	 * @param school:School 学校
	 *
	 * @return List<TestListSubject> 科目別成績リスト　存在しない場合は0件
	 *@throws Exception
	 */
	public List<TestListSubject> filter(int entYear, String classNum,Subject subject,School school) throws Exception{

		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文の条件
		String condition = " where test.SUBJECT_CD  =? and student.ENT_YEAR =? and test.CLASS_NUM =? ";

		try{
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql+condition);
			//プリペアードステートメントに科目コードをバインド
			statement.setString(1,subject.getSubject_cd());
			//プリペアードステートメントに入学年度をバインド
			statement.setInt(2, entYear);
			//プリペアードステートメントにクラス番号をバインド
			statement.setString(3, classNum);
			//プリペアードステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet);

		}catch (Exception e) {
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
