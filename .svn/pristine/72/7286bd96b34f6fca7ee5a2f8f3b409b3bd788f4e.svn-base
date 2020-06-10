package com.ischoolbar.programmer.service.teacher;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.teacher.Teacher;

@Service
public interface TeacherService {
	public int add(Teacher teacher);
	public int edit(Teacher teacher);
	public int delete(String ids);
	public List<Teacher> findList(Map<String,Object> queryMap);
	public List<Teacher> findListByAcademyId(Long id);
	public List<Teacher> findListByDepartmentId(Long id);
	public int getTotal(Map<String,Object> queryMap);
	public Teacher find(Long id);
	public Teacher findById(Long id);
	public int updateCommentNumber(Long id);
	public List<Teacher> findLastCommentList(int pageSize);

}
