package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExcecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		TestDao tDao = new TestDao();// TestDaoを初期化
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		SubjectDao subjectDao = new SubjectDao();//科目Daoを初期化
		List<Test> lists = new ArrayList<>();
		List<Integer> list_point = new ArrayList<>();//pointようのリスト
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメータ―の取得 2
		String entYearStr = req.getParameter("f1");//入学年度
		String classNum = req.getParameter("f2");//クラス番号
		String subject_cd = req.getParameter("f3");//科目コード
		String num = req.getParameter("f4");//回数
		System.out.println(entYearStr + classNum + subject_cd + num);
		List<Test> list = tDao.filter(Integer.parseInt(entYearStr), classNum, subjectDao.get(subject_cd, teacher.getSchool()),Integer.parseInt(num), teacher.getSchool());

		for (Test test : list) {
		String point =	req.getParameter("point_" + test.getStudent().getStudent_no());

		if(point != ""){
			test.setPoint(Integer.parseInt(point));
			test.setSubject(subjectDao.get(subject_cd, teacher.getSchool()));
			test.setNo(Integer.parseInt(num));





			if(Integer.parseInt(point) < 0&& Integer.parseInt(point) > 100){
				errors.put("test_error", "0～100の範囲で入力してください");
				req.setAttribute("test_error", errors);
				req.getRequestDispatcher("test_regist.jsp").forward(req, res);
			}
			lists.add(test);


	}
		}

		tDao.save(lists);
		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}
