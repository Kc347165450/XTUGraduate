package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 新闻评论实体
 * @author llq
 *
 */
@Component
public class Comment {
	private Long id;
	private User user;//用户实体
	private Long userId;
	private Long newsId;//新闻id
	private News news;//新闻实体
	private String nickname;//昵称
	private String content;//评论内容
	private Date createTime;
	public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
