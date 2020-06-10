package com.ischoolbar.programmer.dao.teacher;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.teacher.TeacherComment;

/**
 * µ¼Ê¦ÆÀÂÛDao
 * @author 20161
 *
 */
@Repository
public interface TeacherCommentDao {
	public int add(TeacherComment teacherComment);
//	public int edit(Comment comment);
	public int delete(String ids);
	public List<TeacherComment> findList(Map<String,Object> queryMap);
	public List<TeacherComment> findAll();
	public int getTotal(Map<String,Object> queryMap);
	public TeacherComment find(Long id);

}
