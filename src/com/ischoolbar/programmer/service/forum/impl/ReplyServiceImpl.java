package com.ischoolbar.programmer.service.forum.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.forum.ReplyDao;
import com.ischoolbar.programmer.entity.forum.Reply;
import com.ischoolbar.programmer.service.forum.ReplyService;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    public ReplyDao replyDao;

    public List<Reply> getRepliesOfTopic(Integer topicId) {
        return replyDao.getRepliesOfTopic(topicId);
    }

    public boolean addReply(Reply reply) {
        return replyDao.insert(reply)>0;
    }

    public int repliesNum(Integer topicId) {
        return replyDao.getRepliesNum(topicId);
    }

	@Override
	public int deleteByTopicId(Long id) {
		// TODO Auto-generated method stub
		return replyDao.deleteByTopicId(id);
	}
}
