package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.User;

/**
 * user�û�service
 * @author llq
 *
 */
@Service
public interface UserService {
	public User findByUserId(Long userId);
	public User findByUsername(String username);
	public User findByNickname(String nickname);
	public User findByEmail(String email);
	public int register(User user);
	public int add(User user);
	public int edit(User user);
	public int updateById(User user);//�޸���ͨ�û�
	public int editPassword(User user);
	public int delete(String ids);
	public boolean addCredit(Integer points,Long id);//���ӻ���
	public int getCredit(Long id);//��ȡ����
	public boolean delCredit(Integer points,Long id);//�۳�����
	public List<User> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int getUserCount();
}