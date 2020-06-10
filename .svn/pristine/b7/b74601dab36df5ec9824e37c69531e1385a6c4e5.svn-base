package com.ischoolbar.programmer.dao.forum;
/**
 * 论坛帖子dao
 */

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.News;
import com.ischoolbar.programmer.entity.forum.Topic;
@Repository
public interface TopicDao {
    int deleteByPrimaryKey(Integer id);
    int insert(Topic record);
    int insertSelective(Topic record);
    Topic selectById(Integer id);
    List<Topic> listTopicsAndUsers(Map<String,Object> queryMap);
    List<Topic> listTopicsAndUsersOfTab(Integer tabId);
    List<Topic>  listMostCommentsTopics();
    int updateByPrimaryKeySelective(Topic record);
    int updateByPrimaryKeyWithBLOBs(Topic record);
    int updateByPrimaryKey(Topic record);
    List<Topic> getAllTopics();
    int clickAddOne(Integer id);
    
    public Topic findById(Long id);
    public int delete(String ids);
	public List<Topic> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
    //获取主题总数
    int getTopicsNum();
}