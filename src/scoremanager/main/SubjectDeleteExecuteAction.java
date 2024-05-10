package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		SubjectDao sDao = new SubjectDao();// 学生Dao
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();//エラーメッセージ
		boolean subject_now = false;

		//リクエストパラメータ―の取得 2
		String subject_cd = req.getParameter("subject_cd");
		String name = req.getParameter("name");
		String subnow = req.getParameter("subject_now");




		//DBからデータ取得 3
		Subject subject = sDao.get(subject_cd, teacher.getSchool());// 学生番号から学生インスタンスを取得
		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で4～5が分岐
		if (subnow != null) {
			subject_now = false;
		}
		if (subject != null) {
			// 学生が存在していた場合
			// インスタンスに値をセット
			subject.setSubject_now(subject_now);
			sDao.delete(subject);

		} else {
			errors.put("no", "学生が存在していません");

		}


		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7


		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("subject_cd", subject_cd);
			req.setAttribute("name", name);
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
			return;
		}


		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
}
}
