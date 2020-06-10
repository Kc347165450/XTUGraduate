package com.ischoolbar.programmer.controller.forum;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ischoolbar.programmer.entity.admin.Comment;
import com.ischoolbar.programmer.entity.forum.Reply;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.impl.UserServiceImpl;
import com.ischoolbar.programmer.service.forum.ReplyService;
import com.ischoolbar.programmer.service.forum.impl.ReplyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 回复相关控制类
 */
@RequestMapping("reply")
@Controller
public class ReplyController {

    @Autowired
    public ReplyService replyService;
    @Autowired
    public UserServiceImpl userService;

    /**
     * 添加评论
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ModelAndView addReply(HttpServletRequest request){
        //处理参数
        Integer topicId=Integer.parseInt(request.getParameter("topicId"));
        Long replyUserId=Long.parseLong(request.getParameter("replyUserId"));
        String content=request.getParameter("content");
        //创建reply
        Reply reply=new Reply();
        reply.setTopicId(topicId);
        reply.setReplyUserId(replyUserId);
        reply.setContent(content);
        reply.setCreateTime(new Date());
        reply.setUpdateTime(new Date());
        //执行添加
        boolean ifSucc=replyService.addReply(reply);
        //添加积分
        boolean ifSuccAddCredit=userService.addCredit(1,replyUserId);

        //新建视图
        ModelAndView view=new ModelAndView("redirect:/topic/detail?id="+topicId);
        return view;
    }
    /*@RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commentNews(Reply reply,HttpServletRequest request){
		Map<String, Object> ret = new HashMap<String, Object>();
		Integer topicId=Integer.parseInt(request.getParameter("topicId"));
		Long replyUserId=Long.parseLong(request.getParameter("replyUserId"));
        String content=request.getParameter("content");
		if(reply == null){
			ret.put("type", "error");
			ret.put("msg", "请填写完整的回复信息！");
			return ret;
		}
		if(StringUtils.isEmpty(reply.getContent())){
			ret.put("type", "error");
			ret.put("msg", "请填写评论内容！");
			return ret;
		}
		reply.setTopicId(topicId);
        reply.setReplyUserId(replyUserId);
        reply.setContent(content);
        reply.setCreateTime(new Date());
        reply.setUpdateTime(new Date());
        //执行添加
        boolean ifSucc=replyService.addReply(reply);
        //添加积分
        boolean ifSuccAddCredit=userService.addCredit(1,replyUserId);
		ret.put("type", "success");
		return ret;
	}*/
    
    /**
	 * 分页获取评论
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/get_reply_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getReplyList(Page page,Integer topicId){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("topicId", topicId);
		ret.put("type", "success");
		ret.put("replyList", replyService.getRepliesOfTopic(topicId));
		return ret;
	}
}