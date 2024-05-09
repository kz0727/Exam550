package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable{


	/**
	 * 入学年度：Int
	 */
	private int entYear;


	/**
	 * 学生番号：String
	 */
	private String studentNo;

	/**
	 * 氏名：String
	 */
	private String studentName;


	/**
	 * クラス番号
	 */
	private String classNum;


	/**
	 * 回数:Map<integer,integer>
	 */
	private Map<Integer,Integer> points;


	public int getEntYear() {
		return entYear;
	}


	public String getStudentNo() {
		return studentNo;
	}


	public String getStudentName() {
		return studentName;
	}


	public String getClassNum() {
		return classNum;
	}


	public Map<Integer, Integer> getPoints() {
		return points;
	}


	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}


	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}


	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	public String getPoint(int key) {
		//得点マップから値を取得
		Integer k = points.get(key);
		if (k==null) {
			//得点マップに値が存在しなかった場合、"-"を返却
			return "-";
		}else {
			//得点マップに値が存在した場合、文字列で得点を返却
			return k.toString();
		}

	}


}
