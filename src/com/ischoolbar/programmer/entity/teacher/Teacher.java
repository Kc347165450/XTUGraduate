package com.ischoolbar.programmer.entity.teacher;
/**
 * ��ʦ��
 * @author 20161
 *
 */
public class Teacher {
	private int id;
	private String teacherName;//��ʦ����
	private Academy academy;
	private Department department;
	private Long academyId;//ѧԺ
	private Long departmentId;//רҵ
	private String photo;
	private String tel;//�绰
	private String email;
	private String content;//��ʦ��ϸ��Ϣ
	private int commentNumber = 0;//��������
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Long getAcademyId() {
		return academyId;
	}
	public void setAcademyId(Long academyId) {
		this.academyId = academyId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	

}
