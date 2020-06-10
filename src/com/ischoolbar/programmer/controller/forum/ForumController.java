package com.ischoolbar.programmer.controller.forum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import javax.websocket.Session;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.NewsCategory;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.entity.forum.Tab;
import com.ischoolbar.programmer.entity.forum.Topic;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.NewsCategoryService;
import com.ischoolbar.programmer.service.admin.impl.UserServiceImpl;
import com.ischoolbar.programmer.service.forum.impl.ReplyServiceImpl;
import com.ischoolbar.programmer.service.forum.impl.TabServiceImpl;
import com.ischoolbar.programmer.service.forum.impl.TopicServiceImpl;

/**
 * ��̳Controller
 * @author 20161
 *
 */
@RequestMapping("forum")
@Controller
public class ForumController {
	
	@Autowired
	private NewsCategoryService newsCategoryService;
	@Autowired
    public TopicServiceImpl topicService;
    @Autowired
    public ReplyServiceImpl replyService;
    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public TabServiceImpl tabService;
	
    /**
     * ��Ⱦ��ҳ
     * @param model
     * @return
     */
	@RequestMapping(value = "/cate" ,method = RequestMethod.GET)
	public ModelAndView cate(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		//ȫ������
        List<Topic> topics=topicService.listTopicsAndUsers(queryMap);
        //��ȡͳ����Ϣ
        int topicsNum=topicService.getTopicsNum();
        //��������
        List<Topic> hotestTopics=topicService.listMostCommentsTopics();
        
        model.addObject("tabList",tabService.getAllTabs());//��̳���
        model.addObject("newsCategoryList", newsCategoryService.findAll());//��ҳ������
        model.addObject("topics",topics);
        model.addObject("hotestTopics",hotestTopics);
        model.addObject("topicsNum",topicsNum);
        model.setViewName("forum/cate");
        return  model;
	}
	/**
	 * ��ȡ����
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/credit" ,method = RequestMethod.GET)
	@ResponseBody
	public List getCredit(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			return null;
		}
		List<Integer> list = new ArrayList<Integer>();
		list.add(userService.getCredit(user.getId()));
		return list;
	}
	
	/**
     * ��Ⱦָ�����ҳ��
     */
    @RequestMapping(value="/tab",method=RequestMethod.GET)
	public ModelAndView toTabPage(ModelAndView model,
			@RequestParam(name="id",required=true) String tabNameEn,
			Page page
			){
    	Tab tab=tabService.getByTabNameEn(tabNameEn);
        Integer tabId=tab.getId();
        //ȫ������
        List<Topic> topics=topicService.listTopicsAndUsersOfTab(tabId);
        //��ȡͳ����Ϣ
        int topicsNum=topicService.getTopicsNum();
        //��������
        List<Topic> hotestTopics=topicService.listMostCommentsTopics();
        
        model.addObject("tabList",tabService.getAllTabs());//��̳���
        model.addObject("newsCategoryList", newsCategoryService.findAll());//��ҳ������
        model.addObject("topics",topics);
        model.addObject("topicsNum",topicsNum);
        model.addObject("tab",tab);
        model.addObject("hotestTopics",hotestTopics);
        model.setViewName("forum/tab");
        return  model;
	}
    
    /**
	 * ��ȡ�����б�
	 * @param model
	 * @param keyword
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/search_list",method=RequestMethod.GET)
	public ModelAndView searchList(ModelAndView model,
			@RequestParam(name="keyword",required=false,defaultValue="") String keyword,
			Page page
			){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 10);
		queryMap.put("title", keyword);
		List<Topic> topics=topicService.findList(queryMap);
		model.addObject("tabList",tabService.getAllTabs());//��̳���
        model.addObject("newsCategoryList", newsCategoryService.findAll());//��ҳ������
        model.addObject("topics",topics);
		model.addObject("keyword", keyword);
		model.setViewName("forum/cate");
		return model;
	}

}
