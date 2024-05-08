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
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
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
		//String student_no = "";//入力された学生番号
		int entYear = 0;// 入学年度
		List<Student> students = null;// 学生リスト
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		StudentDao sDao = new StudentDao();//学生Dao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subjectDao = new SubjectDao();//科目DAOを初期化
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		List<Integer> entYearSet = new ArrayList<>();





		System.out.println("1");
		//DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> classlist = cNumDao.filter(teacher.getSchool());
		//ログインユーザーの学校コードをもとに科目一覧を取得
		System.out.println("2");
		List<Subject> subjectlist = subjectDao.filter(teacher.getSchool());
		System.out.println("2-1");


		//ビジネスロジック]

		// リストを初期化

		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
				entYearSet.add(i);
		}


		//レスポンス値をセット
		System.out.println("3");
		// リクエストに入学年度をセット
		request.setAttribute("f1", entYearSet);
		// リクエストにクラス番号をセット
		request.setAttribute("f2", classlist);
		//リクエストに科目をセット
		request.setAttribute("f3", subjectlist);

		System.out.println("4");
		//JSPへフォワード.
		request.getRequestDispatcher("test_list.jsp").forward(request, response);
	}

}
