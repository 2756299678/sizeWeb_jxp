package model;

import java.util.Date;
//----ÿ�����������һ�������¼����ĸ������û��ĵ÷�
public class Exam {

	private int id,shiTiNum,grade,shiJuanID;
	private String username,date;
	
	public Exam(){}
	
	public int getShiJuanID() {
		return shiJuanID;
	}

	public void setShiJuanID(int shiJuanID) {
		this.shiJuanID = shiJuanID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShiTiNum() {
		return shiTiNum;
	}

	public void setShiTiNum(int shiTiNum) {
		this.shiTiNum = shiTiNum;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	
}
