package com.ischoolbar.programmer.dao.forum;
/**
 * 论坛帖子回复Dao
 */

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.forum.Reply;

@Repository
public interface ReplyDao {
    int deleteByPrimaryKey(Long id);
    
    int deleteByTopicId(Long id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Long id);

    List<Reply> getRepliesOfTopic(Integer topicId);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);

    int getRepliesNum(Integer topicId);

}