package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable{


	/**
	 * 科目名：String
	 */
	private String subjectName;

	/**
	 * 科目コード：String
	 */

	private String subjectCd;

	/**まだよくわかってない
	 * num:int
	 */
	private int num;

	/**
	 * 回数：Int
	 */

	private int point;

	/**
	 * ゲッター、セッター
	 */


	public String getSubjectName() {
		return subjectName;
	}

	public String getSubjectCd() {
		return subjectCd;
	}

	public int getNum() {
		return num;
	}

	public int getPoint() {
		return point;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setPoint(int point) {
		this.point = point;
	}



}
