package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class SubjectCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得



		//リクエストパラメータ―の取得 2
		//なし

		//DBからデータ取得 3
		List<String> list = cNumDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得



		//JSPへフォワード 7
		req.getRequestDispatcher("subject_create.jsp").forward(req, res);
	}

}
