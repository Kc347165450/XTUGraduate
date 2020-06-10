package com.ischoolbar.programmer.controller.forum;


import net.sf.jsqlparser.statement.replace.Replace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ischoolbar.programmer.entity.admin.News;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.entity.forum.Reply;
import com.ischoolbar.programmer.entity.forum.Tab;
import com.ischoolbar.programmer.entity.forum.Topic;
import com.ischoolbar.programmer.service.admin.NewsCategoryService;
import com.ischoolbar.programmer.service.admin.impl.UserServiceImpl;
import com.ischoolbar.programmer.service.forum.impl.ReplyServiceImpl;
import com.ischoolbar.programmer.service.forum.impl.TabServiceImpl;
import com.ischoolbar.programmer.service.forum.impl.TopicServiceImpl;

/**
 * 主题相关控制类
 */
@RequestMapping("/topic")
@Controller
public class TopicController {

    @Autowired
    public TopicServiceImpl topicService;
    @Autowired
    public ReplyServiceImpl replyService;
    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public TabServiceImpl tabService;
    @Autowired
	private NewsCategoryService newsCategoryService;

    //log4j对象
    private final Log log = LogFactory.getLog(getClass());
    /**
     * 渲染主题详细页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(ModelAndView model,Integer id){
    	//点击量加一
        boolean ifSucc=topicService.clickAddOne(id);
        //获取主题信息
        Topic topic=topicService.selectById(id);
        //获取主题全部评论
        
        List<Reply> replies=replyService.getRepliesOfTopic(id);
        //获取评论数
        int repliesNum=replyService.repliesNum(id);
        //获取统计信息
        int topicsNum=topicService.getTopicsNum();
//        int usersNum=userService.getUserCount();
        //获取用户信息
//        User user = userService.findByUserId(topic.getUserId());
        //最热主题
        List<Topic> hotestTopics=topicService.listMostCommentsTopics();

        //渲染视图
        model.addObject("tabList",tabService.getAllTabs());//论坛板块
        //model.addObject("newsCategoryList", newsCategoryService.findAll());//首页导航栏
        model.addObject("topic",topic);
        model.addObject("replies",replies);
        model.addObject("repliesNum",repliesNum);
        model.addObject("topicsNum",topicsNum);
//        model.addObject("usersNum",usersNum);
        model.setViewName("forum/topic/detail");
        model.addObject("hotestTopics",hotestTopics);
		return model;
	}
    /**
     * 进入新建主题页面
     */
    @RequestMapping(value = "/newTopic" ,method=RequestMethod.GET)
	public ModelAndView newTopic(ModelAndView model){
//		List<Tab> tabs=tabService.getAllTabs();
        //获取统计信息
//        int topicsNum=topicService.getTopicsNum();
//        int usersNum=userService.getUserCount();

//        model.addObject("tabs",tabs);
        model.addObject("tabList",tabService.getAllTabs());//论坛板块
//        model.addObject("topicsNum",topicsNum);
//        model.addObject("usersNum",usersNum);
		model.setViewName("forum/topic/newTopic");
		return model;
	}

    /**
     * 发表主题
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTopic(HttpServletRequest request){
        ModelAndView indexPage;
        int credit = 1;
        Long userId=Long.parseLong(request.getParameter("UserId"));
        String filepath = request.getParameter("filepath");
        String filename = request.getParameter("filename");
        if (filepath != null) {
			credit += 10;
		}
        //处理参数
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        content = content.replace("\n", "<br>");
        Byte tabId=Byte.parseByte(request.getParameter("tab"));
        //新建Topic
        Topic topic=new Topic();
        topic.setUserId(userId);
        topic.setTitle(title);
        topic.setContent(content);
        topic.setTabId(tabId);
        topic.setFilename(filename);
        topic.setFilepath(filepath);
        topic.setCreateTime(new Date());
        topic.setUpdateTime(new Date());
        //添加topic
        boolean ifSucc=topicService.addTopic(topic);
        boolean ifSuccAddCredit=userService.addCredit(credit,userId);
        if (ifSucc){
            if (log.isInfoEnabled()){
                log.info("添加主题成功!");
            }
        }
        if (ifSuccAddCredit) {
        	if (log.isInfoEnabled()){
                log.info("添加积分成功!");
            }
		}
        indexPage=new ModelAndView("redirect:/forum/cate");

        return  indexPage;
    }
    
    /**
	 * 上传文件
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/upload_file",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadFile(MultipartFile file,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(file == null){
			ret.put("type", "error");
			ret.put("msg", "选择要上传的文件！");
			return ret;
		}
		if(file.getSize() > 1024*1024*1024){
			ret.put("type", "error");
			ret.put("msg", "文件大小不能超过10M！");
			return ret;
		}
		//获取文件后缀
//		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1,file.getOriginalFilename().length());
//		System.out.println(file.getOriginalFilename());
		String savePath = request.getServletContext().getRealPath("/") + "/resources/upload/";
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()){
			//若不存在改目录，则创建目录
			savePathFile.mkdir();
		}
		String filename = file.getOriginalFilename();
		try {
			//将文件保存至指定目录
			file.transferTo(new File(savePath+filename));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "保存文件异常！");
			e.printStackTrace();
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "文件上传成功！");
		ret.put("filename",filename );
		ret.put("filepath",request.getServletContext().getContextPath() + "/resources/upload/" + filename );
		return ret;
	}

}
