package com.ischoolbar.programmer.service.teacher;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.teacher.Academy;

@Service
public interface AcademyService {
	public int add(Academy academy);
	public int delete(Long id);
	public int edit(Academy academy);
	public List<Academy> findList(Map<String,Object> queryMap);
	public List<Academy> findAll();
	public int getTotal(Map<String,Object> queryMap);
	public Academy find(int id);
	public List<Academy> findChildernList(int academyId);//获取本院的所有系别

}
