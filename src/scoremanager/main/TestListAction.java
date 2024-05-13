package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ローカル変数の宣言１
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subjectDao = new SubjectDao();//科目DAOを初期化
		List<Integer> entYearSet = new ArrayList<>();


		//DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> classlist = cNumDao.filter(teacher.getSchool());
		//ログインユーザーの学校コードをもとに科目一覧を取得
		List<Subject> subjectlist = subjectDao.filter(teacher.getSchool());



		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
				entYearSet.add(i);
		}



		//リクエストに入学年度リストをセット
		request.setAttribute("ent_year_set", entYearSet);
		//リクエストにクラスリストをセット
		request.setAttribute("class_list", classlist);
		//リクエストに科目リストをセット
		request.setAttribute("subject_list", subjectlist);

		//JSPへフォワード.
		request.getRequestDispatcher("test_list.jsp").forward(request, response);
	}

}
