package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		SubjectDao sDao = new SubjectDao();//科目Dao
		String subject_cd = "";//科目コード
		String name = "";//科目名
		Subject subject = null;//学生
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		Map<String, String> errors = new HashMap<>();// エラーメッセージ


		//リクエストパラメータ―の取得 2
		subject_cd = req.getParameter("subject_cd");//科目コード
		name = req.getParameter("name");//科目名

		//DBからデータ取得 3
//		subject = sDao.get(subject_no);// 学生番号から学生インスタンスを取得
//		List<String> list = cNumDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得


		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐
		// 現在を起点に前後10年をリストに追加

			if (subject == null) {// 学生が未登録だった場合
				// 学生インスタンスを初期化
				subject = new Subject();
				// インスタンスに値をセット
				subject.setSubject_cd(subject_cd);
				subject.setName(name);
				subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
				// 学生を保存
				sDao.save(subject);
			} else {//入力された学番がDBに保存されていた場合
				errors.put("no", "学生番号が重複しています");
			}



//		if (subject == null) {// 科目が未登録だった場合
//			// 学生インスタンスを初期化
//			subject = new Subject();
//			// インスタンスに値をセット
//			subject.setSubject_cd(subject_no);
//			subject.setName(name);
//			subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
//			// 学生を保存
//			sDao.save(subject);
//		} else {//入力された学番がDBに保存されていた場合
//			errors.put("no", "学生番号が重複しています");
//		}



		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7

		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("subject_cd", subject_cd);
			req.setAttribute("name", name);
			req.getRequestDispatcher("student_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
	}

}


