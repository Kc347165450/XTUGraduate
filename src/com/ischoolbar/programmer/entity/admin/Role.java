package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * ��ɫroleʵ��
 * @author llq
 *
 */
@Component
public class Role {
	
	private Long id;
	
	private String name;
	
	private String remark;//��ɫ��ע

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
