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
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction  extends Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ローカル変数の宣言 1
			HttpSession session = request.getSession();//セッション

			TestListSubjectDao testSubDao = new TestListSubjectDao();//科目別成績DAO
			SubjectDao subDao = new SubjectDao();//科目DAO
 			int entYear;//入学年度
			Subject subject = null;//科目名
			String subject_cd = "";//科目コード
			String classNum = "";//クラス番
			String student_no = "";//学生番号
			Map<String, String> errors = new HashMap<>();// エラーメッセージ
			Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
			LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
			int year = todaysDate.getYear();// 現在の年を取得
			List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化

			//リクエストパラメーターの取得
			entYear = Integer.parseInt(request.getParameter("ent_year"));//入学年度
			subject_cd =request.getParameter("subject_cd");//科目名
			classNum = request.getParameter("class_num");//クラス番号

			//DBからデータ取得
			subject = subDao.get(subject_cd, teacher.getSchool());//科目コードから科目を取得
			//入学年度、クラス番号、科目インスタンス、学校から、科目別成績一覧を取得
			List<TestListSubject> list = testSubDao.filter(entYear, classNum, subject, teacher.getSchool());

			//ビジネスロジック
			//DBへデータ保存
			//条件で手順４～５の内容が分岐
			for (int i = year - 10; i < year + 10; i++) {
				entYearSet.add(i);
			}// 現在を起点に前後10年をリストに追加

			//入学年度、科目番号、クラス番号が入力されていなかった場合
			if (entYear == 0 && subject_cd == null && classNum == null) {// 入学年度が選択されていない場合
				errors.put("num", "入学年度を選択してください");

			}else if (entYear == 0) {//入学年度が入力されていなかった場合
				errors.put("ent_year", "入学年度を選択してください");

			}else if (subject_cd == null) {
				errors.put("subject_cd", "科目名を選択してください");

			}else if(classNum == null) {//クラス番号が入力されていなかった場合
				errors.put("class_num", "クラス番号を選択してください");
			}

			//エラーがあったかどうかで手順6~7の内容が分岐
			//レスポンス値をセット 6
			//JSPへフォワード 7

			request.setAttribute("test_list_subject", list);

			if(!errors.isEmpty()){
				//リクエスト属性をセット
				request.setAttribute("errors", errors);
				request.setAttribute("f1", entYear);
				request.setAttribute("f2", classNum);
				request.setAttribute("f3",subject );
				request.setAttribute("f4", student_no);
				request.getRequestDispatcher("test_list.jsp").forward(request, response);
				return;
			}

			request.getRequestDispatcher("test_list_student.jsp").forward(request, response);


	}




}
