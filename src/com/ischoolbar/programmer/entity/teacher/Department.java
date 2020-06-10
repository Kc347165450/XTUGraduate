package com.ischoolbar.programmer.entity.teacher;
/**
 * ×¨Òµ±í
 * @author 20161
 *
 */
public class Department {
	private int id;
	private int academyId;
	private String departmentName;
	private Academy academy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	

}
