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


}
