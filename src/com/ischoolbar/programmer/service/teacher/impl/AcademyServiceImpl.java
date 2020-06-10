package com.ischoolbar.programmer.service.teacher.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.teacher.AcademyDao;
import com.ischoolbar.programmer.entity.teacher.Academy;
import com.ischoolbar.programmer.service.teacher.AcademyService;

@Service
public class AcademyServiceImpl implements AcademyService {
	
	@Autowired
	private AcademyDao academyDao;

	@Override
	public int add(Academy academy) {
		// TODO Auto-generated method stub
		return academyDao.add(academy);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return academyDao.delete(id);
	}

	@Override
	public int edit(Academy academy) {
		// TODO Auto-generated method stub
		return academyDao.edit(academy);
	}

	@Override
	public List<Academy> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return academyDao.findList(queryMap);
	}

	@Override
	public List<Academy> findAll() {
		// TODO Auto-generated method stub
		return academyDao.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return academyDao.getTotal(queryMap);
	}

	@Override
	public Academy find(int id) {
		// TODO Auto-generated method stub
		return academyDao.find(id);
	}
	
	@Override
	public List<Academy> findChildernList(int academyId){
		return academyDao.findChildernList(academyId);
	}

}
