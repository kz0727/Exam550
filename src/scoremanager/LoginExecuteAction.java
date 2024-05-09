package scoremanager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";
		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		Teacher teacher = new Teacher();
		TeacherDao tDao = new TeacherDao();

		//リクエストパラメータ―の取得 2
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		//DBからデータ取得 3
		teacher = tDao.login(id, password);

		// ティチャーがヌルの場合はjspにフォワード
		if(teacher == null){
			errors.put("kome", "ログインに失敗しました。IDまたはパスワードが違います。");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("login.jsp").forward(req, res);
		} else {
			//ビジネスロジック 4
			// 認証済みフラグを立てる
			teacher.setAuthenticated(true);
			//Sessionを有効にする
			HttpSession session = req.getSession(true);
			//セッションに"user"という変数名で値はTeacher変数の中身
			session.setAttribute("user", teacher);

			//DBへデータ保存 5
			//なし
			//レスポンス値をセット 6
			//なし
			//JSPへフォワード 7
			//req.getRequestDispatcher("main/Menu.action").forward(req, res);

			//リダイレクト
			url = "main/Menu.action";
			res.sendRedirect(url);
		}

	}

}
