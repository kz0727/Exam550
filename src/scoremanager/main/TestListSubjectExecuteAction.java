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
 			int entYear = 0;//入学年度
			Subject subject = null;//科目名
			String entYearStr="";// 入力された入学年度
			String subject_cd = "";//科目コード
			String classNum = "";//クラス番号
			Map<String, String> errors = new HashMap<>();// エラーメッセージ
			Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
			LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
			int year = todaysDate.getYear();// 現在の年を取得
			List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化

			//リクエストパラメーターの取得
			entYearStr =request.getParameter("f1");//入学年度
			classNum=request.getParameter("f2");//クラス番号
			subject_cd = request.getParameter("f3");//科目名

			request.setAttribute("f1", entYearSet);
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", subject_cd);
			System.out.println(entYearStr);
			System.out.println(classNum);
			System.out.println(subject_cd);

			//入学年度が送信されていた場合
			if (entYearStr != null){
				//数値に変換
				entYear = Integer.parseInt(entYearStr);
			}

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
			if(entYear == 0|| classNum.equals("0") || subject_cd.equals("0")){
				errors.put("select", "入学年度とクラス番号と科目を入力してください");
			}

			//エラーがあったかどうかで手順6~7の内容が分岐
			//レスポンス値をセット 6
			//JSPへフォワード 7

			request.setAttribute("tsublist", list);

			request.getRequestDispatcher("test_list_student.jsp").forward(request, response);


	}




}
