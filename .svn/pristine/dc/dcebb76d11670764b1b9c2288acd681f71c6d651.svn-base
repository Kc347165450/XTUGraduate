package com.ischoolbar.programmer.dao.forum;
/**
 * 论坛板块dao
 */

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.NewsCategory;
import com.ischoolbar.programmer.entity.forum.Tab;

@Repository
public interface TabDao {
	/**
	 * 前端页面
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);
    int insert(Tab record);
    int insertSelective(Tab record);
    Tab selectByPrimaryKey(Integer id);
    Tab getByTabNameEn(String tabName);
    int updateByPrimaryKeySelective(Tab record);
    int updateByPrimaryKey(Tab record);
    List<Tab> getAllTabs();
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