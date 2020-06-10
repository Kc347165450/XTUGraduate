package com.ischoolbar.programmer.service.teacher.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.teacher.TeacherDao;
import com.ischoolbar.programmer.entity.teacher.Teacher;
import com.ischoolbar.programmer.service.teacher.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherDao teacherDao;

	@Override
	public int add(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.add(teacher);
	}

	@Override
	public int edit(Teacher teacher) {
		// TODO Auto-generated method stub
		return teacherDao.edit(teacher);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return teacherDao.delete(ids);
	}

	@Override
	public List<Teacher> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return teacherDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return teacherDao.getTotal(queryMap);
	}

	@Override
	public Teacher find(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.find(id);
	}

	@Override
	public int updateCommentNumber(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.updateCommentNumber(id);
	}

	@Override
	public List<Teacher> findLastCommentList(int pageSize) {
		// TODO Auto-generated method stub
		return teacherDao.findLastCommentList(pageSize);
	}

	@Override
	public List<Teacher> findListByAcademyId(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.findListByAcademyId(id);
	}

	@Override
	public List<Teacher> findListByDepartmentId(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.findListByDepartmentId(id);
	}

	@Override
	public Teacher findById(Long id) {
		// TODO Auto-generated method stub
		return teacherDao.findById(id);
	}
	

}
