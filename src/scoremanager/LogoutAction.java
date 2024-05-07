package scoremanager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		 res.setContentType("text/html; charset=Shift_JIS");
		 PrintWriter out = res.getWriter();
		 HttpSession session = req.getSession(true);
	     session.invalidate();

		//リクエストパラメータ―の取得 2
		//DBからデータ取得 3
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//JSPへフォワード 7
		req.getRequestDispatcher("logout.jsp").forward(req, res);

	}

}
