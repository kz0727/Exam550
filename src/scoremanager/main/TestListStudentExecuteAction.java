package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
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
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

		//リクエストパラメータの取得
		student_no = request.getParameter("student_no");//学生番号

		//DBからデータ取得 3
		student = sDao.get(student_no);// 学生番号から学生インスタンスを取得

		if(student != null){//学生が存在していた場合

		}else {
			errors.put("no", "学生が存在しませんでした。");
		}

		List<TestListStudent> list = tDao.filter(student);//学生別成績リストを取得

		if(list == null ){//学生に成績が存在しなかった場合
			errors.put("list", "学生に成績が存在しませんでした。");
		}

		//リクエストに学生別成績リストをセット
		request.setAttribute("test_list_student", list);



		request.getRequestDispatcher("test_list_student.jsp").forward(request, response);

	}



}
