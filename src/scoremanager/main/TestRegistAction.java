package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
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
			String subject_cd ="";//入力された科目
			int entYear = 0;// 入学年度
			LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
			int year = todaysDate.getYear();// 現在の年を取得
			String num = ""; //入力された回数
			int numi = 0;
			List<Test> test = null;
			Subject subject = null;
			TestDao tDao = new TestDao();
			SubjectDao sDao = new SubjectDao();

			ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
			Map<String, String> errors = new HashMap<>();// エラーメッセージ
			//リクエストパラメーター
			entYearStr = req.getParameter("f1");
			classNum = req.getParameter("f2");
			subject_cd = req.getParameter("f3");
			num = req.getParameter("f4");

			try{
				numi = Integer.parseInt(num);

			}catch (NumberFormatException e) {
				System.out.println(e);
			}
			//DBからデータ取得 3

			// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
			List<String> list = cNumDao.filter(teacher.getSchool());
			List<Subject> slist = sDao.filter(teacher.getSchool());
			subject = sDao.get(subject_cd, teacher.getSchool());

			// リストを初期化
			List<Integer> entYearSet = new ArrayList<>();
			// 10年前から1年後まで年をリストに追加
			for (int i = year - 10; i < year + 1; i++) {
				entYearSet.add(i);

			}
			if (entYearStr !=null){
				entYear = Integer.parseInt(entYearStr);
			}

			System.out.println(entYearStr + classNum + subject_cd + num);
			if(entYearStr != null&& classNum != null&& subject_cd !=null && num != null) {
				test = tDao.filter(entYear, classNum, subject, numi, teacher.getSchool());
				System.out.println(test);
				req.setAttribute("tests", test);
			}



			req.setAttribute("f1", entYear);
			// リクエストにクラス番号をセット
			req.setAttribute("f2", classNum);
			req.setAttribute("f3", subject_cd);
			req.setAttribute("f4", num);

			// リクエストに学生リストをセット

			// リクエストにデータをセット
			req.setAttribute("class_num_set", list);
			req.setAttribute("subject", subject);
			req.setAttribute("ent_year_set", entYearSet);
			req.setAttribute("slist", slist);
			//JSPへフォワード 7
			req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}