package bean;

import java.io.Serializable;

public class Subject implements Serializable {

	//科目コード String
	private String subject_cd;

	//科目名 String
	private String name;

	//所属校 School
	private School school;

	private boolean subject_now;


    //ゲッター

	public boolean isSubject_now() {
		return subject_now;
	}

	public String getSubject_cd() {
		return subject_cd;
	}

	public String getName() {
		return name;
	}

	public School getSchool() {
		return school;
	}


//	セッター

	public void setSubject_now(Boolean subject_now) {
		this.subject_now = subject_now;
	}

	public void setSubject_cd(String subject_cd) {
		this.subject_cd = subject_cd;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSchool(School school) {
		this.school = school;
	}


}
