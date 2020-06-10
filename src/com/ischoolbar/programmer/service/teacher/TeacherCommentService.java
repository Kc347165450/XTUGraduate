package com.ischoolbar.programmer.service.teacher;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.teacher.TeacherComment;

@Service
public interface TeacherCommentService {
	public int add(TeacherComment teacherComment);
//	public int edit(Comment comment);
	public int delete(String ids);
	public List<TeacherComment> findList(Map<String,Object> queryMap);
	public List<TeacherComment> findAll();
	public int getTotal(Map<String,Object> queryMap);
	public TeacherComment find(Long id);
}
