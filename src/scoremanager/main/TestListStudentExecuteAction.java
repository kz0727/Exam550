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
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//ローカル変数の宣言１
		HttpSession session = request.getSession();//セッション
		TestListStudentDao tDao = new TestListStudentDao();
		StudentDao sDao = new StudentDao();//学生Dao
		String student_no = "";//学生番号
		Student student = null;//学生
		String titlestu = "学生";//タイトル
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subjectDao = new SubjectDao();//科目DAOを初期化
		List<Integer> entYearSet = new ArrayList<>();//入学年度
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

		//リクエストパラメータの取得
		student_no = request.getParameter("student_no");//学生番号
		request.setAttribute("f4", student_no);

		//DBからデータ取得 3
		student = sDao.get(student_no);// 学生番号から学生インスタンスを取得
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> classlist = cNumDao.filter(teacher.getSchool());
		//ログインユーザーの学校コードをもとに科目一覧を取得
		List<Subject> subjectlist = subjectDao.filter(teacher.getSchool());


		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
				entYearSet.add(i);
		}

		if(student != null){//学生が存在していた場合
			List<TestListStudent> list = tDao.filter(student,teacher.getSchool());//学生別成績リストを取得
			//リクエストに学生別成績リストと学生情報をセット
			request.setAttribute("list", list);
			request.setAttribute("student", student);

		//学生が存在しなかった場合
		}else {
			//エラーにコメントを格納
			errors.put("no", "学生が存在しませんでした。");
		}

		//リクエストに入学年度リストをセット
		request.setAttribute("ent_year_set", entYearSet);
		//リクエストにクラスリストをセット
		request.setAttribute("class_list", classlist);
		//リクエストに科目リストをセット
		request.setAttribute("subject_list", subjectlist);
		//リクエストにタイトル情報をセット
		request.setAttribute("titlestu", titlestu);


		//成績一覧にフォワード
		request.getRequestDispatcher("test_list_student.jsp").forward(request, response);

	}



}
