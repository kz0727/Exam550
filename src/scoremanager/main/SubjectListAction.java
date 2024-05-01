package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー


		List<Subject> subjects = null;// 学生リスト
		SubjectDao sDao = new SubjectDao();//科目Dao

		//DBからデータ取得 3

		subjects = sDao.filter(teacher.getSchool());

		//DBへデータ保存 5
		//なし

		// リクエストに学生リストをセット
		req.setAttribute("subjects", subjects);

		//JSPへフォワード 7
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}



}
