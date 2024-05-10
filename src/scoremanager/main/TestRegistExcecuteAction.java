package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExcecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		TestDao tDao = new TestDao();// testDaoを初期化
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		SubjectDao subjectDao = new SubjectDao();// クラス番号Daoを初期化
		List<Test> lists = new ArrayList<>();
		Map<String, String> errors = new HashMap<>();//エラーメッセージ
		System.out.print('a');

		//リクエストパラメータ―の取得 2
		String entYearStr= req.getParameter("f1");
		String Num = req.getParameter("f2");
		String subjectCd = req.getParameter("f3");
		String classNum = req.getParameter("f4");
		List<Test>list=

		// 在学フラグにチェックが入っていた場合
		if (isAttendStr != null) {
			// 在学フラグを立てる
			isAttend = true;
		}

		//DBからデータ取得 3
		Student student = sDao.get(student_no);// 学生番号から学生インスタンスを取得
		List<String> list = cNumDao.filter(teacher.getSchool());//ログインユーザーの学校コードをもとにクラス番号の一覧を取得

		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で4～5が分岐
		if (point != null) {
			// 学生が存在していた場合
			// インスタンスに値をセット
			test.setName(name);
			student.setClassNum(classNum);
			// 学生を保存
			sDao.save(student);
		} else {
			errors.put("no", "学生が存在していません");
		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7
		req.setAttribute("class_num_set", list);

		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("ent_year", entYearStr);
			req.setAttribute("student_no", student_no);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			return;
		}

		req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
	}
}
