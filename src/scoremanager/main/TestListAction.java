package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class TestListAction extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ローカル変数の宣言１
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr = "";//入力された入学年度
		String classNum = "";//入力されたクラス番号
		String subjectCd = "";//入力された科目
		String student_no = "";//入力された学生番号
		int entYear = 0;// 入学年度
		List<Student> students = null;// 学生リスト
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		StudentDao sDao = new StudentDao();//学生Dao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();// エラーメッセージ



		//リクエストパラメータ―の取得 2
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		subjectCd = request.getParameter("f3");
		student_no = request.getParameter("f4");


		//DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		//後からここにfilterを書く


		//ビジネスロジック]

		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
				entYearSet.add(i);
		}


		//レスポンス値をセット

		// リクエストに入学年度をセット
		request.setAttribute("f1", entYear);
		// リクエストにクラス番号をセット
		request.setAttribute("f2", classNum);
		//リクエストに科目をセット
		request.setAttribute("f3", subjectCd);
		//リクエストに学生番号をセット
		request.setAttribute("f4", student_no);

		//JSPへフォワード.
		request.getRequestDispatcher("test_list.jsp").forward(request, response);
	}

	}

}
