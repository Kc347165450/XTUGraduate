package com.ischoolbar.programmer.controller.home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.service.admin.NewsCategoryService;
import com.ischoolbar.programmer.service.admin.NewsService;

/**
 * 前台页面首页控制器
 * @author llq
 *
 */
@RequestMapping("/index")
@Controller
public class IndexController {
	
	@Autowired
	private NewsCategoryService newsCategoryService;
	
	@Autowired
	private NewsService newsService;
	
	/**
	 * 系统首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 5);
		model.addObject("newsCategoryList", newsCategoryService.findAll());//首页导航栏
		model.addObject("newsList", newsService.findList(queryMap));
		model.setViewName("home/index/index");
		return model;
	}
	
	/**
	 * 获取网站基本信息
	 * @return
	 */
	@RequestMapping(value="/get_site_info",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSiteInfo(){
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("type", "success");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 99999);
		retMap.put("totalArticle", newsService.getTotal(queryMap));
		retMap.put("siteDays", getDays("2018-02-22"));
		return retMap;
	}
	
	private long getDays(String data){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		try {
			startDate = sdf.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date endDate = new Date();
		long time = (endDate.getTime() - startDate.getTime())/1000/3600/24;
		return time;
	}
	
	/**
	 * 前台退出注销功能
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/userlogout",method=RequestMethod.GET)
	public String userlogout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		session.setAttribute("role", null);
		request.getSession().setAttribute("userMenus", null);
		return "redirect:index";
	}
}
