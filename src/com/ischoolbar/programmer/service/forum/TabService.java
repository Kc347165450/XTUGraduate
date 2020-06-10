package com.ischoolbar.programmer.service.forum;


import com.ischoolbar.programmer.entity.forum.Tab;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public interface TabService {
    List<Tab> getAllTabs();

    Tab getByTabNameEn(String tabName);
    
    /**
     * 管理页面
     * @param record
     * @return
     */
    public int add(Tab record);
	public int edit(Tab record);
	public int delete(Long id);
	public List<Tab> findList(Map<String,Object> queryMap);
	public List<Tab> findAll();
	public int getTotal(Map<String,Object> queryMap);
	public Tab find(Long id);
}
