package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao{

	public Subject get(String subject_cd, School school) throws Exception {
		Subject subject = new Subject();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメンと
		PreparedStatement statement = null;

		try{
			//"select * from test where subject_cd=? and subject_cd=? and school_cd=? and no=?"
			//プリペアードステートステートメンとにSQL文をセット
			statement = connection.prepareStatement("select * from subject where subject_cd=? ");
			//プリペアードステートメントに科目コードをバインド
			statement.setString(1, subject_cd);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();
			//学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				//リザルトセットが存在する場合
				//科目インスタンスに検索結果をセット
				subject.setSubject_cd(rSet.getString("subject_cd"));
				subject.setName(rSet.getString("name"));
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
				subject.setSubject_now(rSet.getBoolean("subject_now"));
			} else {
				//リザルトセットが存在しない場合
				//学生ｎインスタンスにnullをセット
				subject = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメンとをとじる
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
		return subject;
	}

	private String baseSql = "select * from subject where school_cd=? ";

	private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
		List<Subject> list = new ArrayList<>();
		try{
			//リザルトセットを全件走査
			while (rSet.next()) {
				//科目インスタンスを初期化
				Subject subject = new Subject();
				//科目インスタンスに検索結果をセット
				subject.setSubject_cd(rSet.getString("subject_cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(school);
				subject.setSubject_now(rSet.getBoolean("subject_now"));
				//リストに追加
				list.add(subject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Subject> filter(School school) throws Exception {

		List<Subject> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文のソート
		String condition_subject_now = "and subject_now=true";
		String order = " order by subject_cd asc";


		try{
			statement = connection.prepareStatement(baseSql + condition_subject_now + order);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getSchool_cd());
			//プリペアードステートメントを実行
			rSet = statement.executeQuery();
			list = postFilter(rSet, school);

		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメンとをとじる
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

	public boolean save(Subject subject) throws Exception {

		Connection connection = getConnection();
		//プリペアードステートメンと
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//	データベースから科目を取得
			Subject old = get(subject.getSubject_cd(), subject.getSchool());
			if (old == null) {
				//学生が存在しなかった場合
				//プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement("insert into subject (subject_cd, name, school_cd, subject_now) values(?, ?, ?, ?)");
				//プリペアードステートメントに値をバインド
				statement.setString(1,  subject.getSubject_cd());
				statement.setString(2,  subject.getName());
				statement.setString(3,  subject.getSchool().getSchool_cd());
				statement.setBoolean(4, subject.isSubject_now());
			} else {
				//科目が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection.prepareStatement("update subject set name=? where subject_cd=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1,  subject.getName());
				statement.setString(2,  subject.getSubject_cd());
			}
			//プリペアードステートメントにを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメンとをとじる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
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
		}
		if (count > 0){
			//実行件数が1件以上ある場合
			return true;
		} else {
			//実行件数が0件の場合
			return false;
	}
	}


	public boolean delete(Subject subject) throws Exception {
		Connection connection = getConnection();
		//プリペアードステートメンと
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try{
			//	データベースから科目を取得

			//プリペアードステートメントにUPDATE文をセット
			statement = connection.prepareStatement("update subject set subject_now=? where subject_cd=? ");
			//プリペアードステートメントに値をバインド
			statement.setBoolean(1,  subject.isSubject_now());
			statement.setString(2,  subject.getSubject_cd());

			//プリペアードステートメントにを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメンとをとじる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
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
		}
		if (count > 0){
			//実行件数が1件以上ある場合
			return true;
		} else {
			//実行件数が0件の場合
			return false;
	}
	}
}
