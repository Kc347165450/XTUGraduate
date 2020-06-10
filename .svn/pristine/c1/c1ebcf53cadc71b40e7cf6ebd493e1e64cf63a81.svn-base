package com.ischoolbar.programmer.service.forum.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.forum.TabDao;
import com.ischoolbar.programmer.entity.forum.Tab;
import com.ischoolbar.programmer.service.forum.TabService;

import java.util.List;
import java.util.Map;

@Service
public class TabServiceImpl implements TabService {

    @Autowired
    public TabDao tabDao;

    public List<Tab> getAllTabs() {
        return tabDao.getAllTabs();
    }

    public Tab getByTabNameEn(String tabNameEn) {
        return tabDao.getByTabNameEn(tabNameEn);
    }

	@Override
	public int add(Tab record) {
		// TODO Auto-generated method stub
		return tabDao.add(record);
	}

	@Override
	public int edit(Tab record) {
		// TODO Auto-generated method stub
		return tabDao.edit(record);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return tabDao.delete(id);
	}

	@Override
	public List<Tab> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return tabDao.findList(queryMap);
	}

	@Override
	public List<Tab> findAll() {
		// TODO Auto-generated method stub
		return tabDao.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return tabDao.getTotal(queryMap);
	}

	@Override
	public Tab find(Long id) {
		// TODO Auto-generated method stub
		return tabDao.find(id);
	}
}
