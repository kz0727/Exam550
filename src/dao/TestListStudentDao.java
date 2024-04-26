package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{

	/**
	 * baseSql:String 共通SQL文　プライベート
	 */

	private String baseSql = "";


	/**
	 *  postFilterメソッド　フィルター後のリストへの格納処理
	 *
	 *  @param rSet:リザルトセット
	 *  @return List<TestListStudent>学生別成績リスト 存在しない場合は0件のリスト
	 *  @throws Exception
	 */
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception{
		List<TestListStudent> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while(rSet.next()){
				//学生別成績インスタンスを初期化
				TestListStudent test = new TestListStudent();

				//学生別成績インスタンスに検索結果をセット
				test.setSubjectName(rSet.getString("name"));

			}
		}
	}

	/**
	 * filterメソッド 学生を指定して学生別成績一覧の取得
	 *
	 * @param student:Student 学生
	 *
	 * @return List<TestListStudent> 学生別成績リスト　存在しない場合は0件
	 * @throws Exception
	 */
	public List<TestListStudent> filter(School school)throws Exception{
		//リストを初期化
		List<TestListStudent> list =new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;


	}

}
