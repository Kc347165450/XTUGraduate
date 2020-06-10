package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.MessageDao;
import com.ischoolbar.programmer.entity.admin.Comment;
import com.ischoolbar.programmer.entity.admin.Message;
import com.ischoolbar.programmer.service.admin.MessageService;
@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDao messageDao;
	

	@Override
	public int add(Message message) {
		// TODO Auto-generated method stub
		return messageDao.add(message);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return messageDao.delete(ids);
	}

	@Override
	public List<Message> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return messageDao.findList(queryMap);
	}

	@Override
	public List<Message> findAll() {
		// TODO Auto-generated method stub
		return messageDao.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return messageDao.getTotal(queryMap);
	}

	@Override
	public Message find(Long id) {
		// TODO Auto-generated method stub
		return messageDao.find(id);
	}

	@Override
	public String getEmailById(Long id) {
		// TODO Auto-generated method stub
		return messageDao.getEmailById(id);
	}
	

}
