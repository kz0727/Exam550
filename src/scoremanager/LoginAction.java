package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LoginAction extends Action{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session=req.getSession();

		//リクエストパラメータ―の取得 2
		String login=req.getParameter("login");
		String password=req.getParameter("password");

		//DBからデータ取得 3

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
