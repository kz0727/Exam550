package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session=req.getSession();

		//リクエストパラメータ―の取得 2
		String id=req.getParameter("id");
		String password=req.getParameter("password");

		//DBからデータ取得 3
		TeacherDao dao=new TeacherDao();
		Teacher teacher=dao.login(id, password);
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7
		req.getRequestDispatcher("login.jsp").forward(req, res);

	}

}
