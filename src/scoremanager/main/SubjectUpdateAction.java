package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		SubjectDao sDao = new SubjectDao();//科目Dao
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメータ―の取得 2
		String subject_cd = req.getParameter("subject_cd");//学番
		//DBからデータ取得 3
		Subject subject = sDao.get(subject_cd,teacher.getSchool());//学生番号から学生インスタンスを取得
		List<String> list = cNumDao.filter(teacher.getSchool());//ログインユーザーの学校コードをもとにクラス番号の一覧を取得


		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//条件で手順4~6の内容が分岐
		if (subject != null) {// 学生が存在していた場合
			req.setAttribute("subject_cd", subject.getSubject_cd());
			req.setAttribute("name", subject.getName());
		} else {// 学生が存在していなかった場合
			errors.put("subject_cd", "科目が存在していません");
			req.setAttribute("errors", errors);
		}

		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
}
}
