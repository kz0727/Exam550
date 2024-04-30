package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	//ローカル変数の宣言１
			HttpSession session = req.getSession();//セッション
			Teacher teacher = (Teacher)session.getAttribute("user");

			String entYearStr="";// 入力された入学年度
			String classNum = "";//入力されたクラス番号
			String subject ="";//入力された科目
			int entYear = 0;// 入学年度
			LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
			int year = todaysDate.getYear();// 現在の年を取得
			String num = ""; //入力された回数
			List<Test> test = null;
			TestDao tDao = new TestDao();
			ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
			Map<String, String> errors = new HashMap<>();// エラーメッセージ
			//リクエストパラメーター
			entYearStr = req.getParameter("f1");
			classNum = req.getParameter("f2");
			subject = req.getParameter("f3");
			num = req.getParameter("f4");

			//DBからデータ取得 3

			// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
			List<String> list = cNumDao.filter(teacher.getSchool());

			// リストを初期化
			List<Integer> entYearSet = new ArrayList<>();
			// 10年前から1年後まで年をリストに追加
			for (int i = year - 10; i < year + 1; i++) {
				entYearSet.add(i);

			}
			req.setAttribute("f1", entYear);
			// リクエストにクラス番号をセット
			req.setAttribute("f2", classNum);
			req.setAttribute("f3", subject);
			req.setAttribute("f4", num);

			// リクエストに学生リストをセット
			req.setAttribute("test", test);
			// リクエストにデータをセット
			req.setAttribute("class_num_set", list);
			req.setAttribute("ent_year_set", entYearSet);
			//JSPへフォワード 7
			req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}