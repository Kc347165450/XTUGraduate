package com.ischoolbar.programmer.service.forum;


import com.ischoolbar.programmer.entity.forum.Reply;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface ReplyService {

    List<Reply> getRepliesOfTopic(Integer topicId);
    
    int deleteByTopicId(Long id);

    boolean addReply(Reply reply);

    int repliesNum(Integer topicId);
}
