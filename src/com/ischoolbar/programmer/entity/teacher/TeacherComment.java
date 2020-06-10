package com.ischoolbar.programmer.entity.teacher;

import java.util.Date;

/**
 * 导师评论实体
 * @author 20161
 *
 */
public class TeacherComment {
	private Long id;
	private Teacher teacher;
	private Long teacherId;
	private String nickname;//昵称
	private String identify;//导师辨识特征
	private String ability;//导师学术水平
	private String funds;//导师科研经费
	private String relationship;//师生关系
	private String future;//学生前途
	private Long thumbsUp;//点赞
	private Long thumbsDown;//点踩
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public Long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public String getFunds() {
		return funds;
	}
	public void setFunds(String funds) {
		this.funds = funds;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getFuture() {
		return future;
	}
	public void setFuture(String future) {
		this.future = future;
	}
	public Long getThumbsUp() {
		return thumbsUp;
	}
	public void setThumbsUp(Long thumbsUp) {
		this.thumbsUp = thumbsUp;
	}
	public Long getThumbsDown() {
		return thumbsDown;
	}
	public void setThumbsDown(Long thumbsDown) {
		this.thumbsDown = thumbsDown;
	}
	
}
