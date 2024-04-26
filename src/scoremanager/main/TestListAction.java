package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class TestListAction extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ローカル変数の宣言１
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
	}


	/**
	 * setTestListSubjectメソッド　今は分からない
	 *
	 * @param req:HttpServletRequest リクエスト
	 * @param res: HttpServletResponse　レスポンス
	 *
	 * @return void
	 */
	private void setTestListSubject(HttpServletRequest req,HttpServletResponse res)throws Exception{

	}

	/**
	 * setTestListStudentメソッド　今はわからない
	 * @param req:HttpServletRequest リクエスト
	 * @param res: HttpServletResponse レスポンス
	 */

	private void setTestListStudent(HttpServletRequest req,HttpServletResponse res)throws Exception{

	}
}
