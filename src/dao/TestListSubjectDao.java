package dao;

import java.sql.ResultSet;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{

	/**
	 * baseSql:String 共通SQL文　プライベート
	 */

	private String baseSql = "select * from student where school_cd=?";


	/**
	 * postFilterメソッド　フィルター後のリストへの格納処理
	 *
	 * @param rSet:リザルトセット
	 *
	 * @return 科目別の学生の成績リスト:List<TestListSubject>存在しない場合は0件のリスト
	 * @throws Exception
	 */
	private List<TestListSubject> postFilter(ResultSet rSet)throws Exception{

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

	}

}
