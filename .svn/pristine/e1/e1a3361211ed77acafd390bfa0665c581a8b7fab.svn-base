package com.ischoolbar.programmer.controller.home;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Comment;
import com.ischoolbar.programmer.entity.admin.News;
import com.ischoolbar.programmer.entity.admin.NewsCategory;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.CommentService;
import com.ischoolbar.programmer.service.admin.NewsCategoryService;
import com.ischoolbar.programmer.service.admin.NewsService;

/**
 * 前台新闻控制器
 * @author llq
 *
 */
@RequestMapping("/news")
@Controller
public class HomeNewsController {
	
	@Autowired
	private NewsCategoryService newsCategoryService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private CommentService commentService;
	
	
	/**
	 * 查看新闻详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(ModelAndView model,Long id){
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		News news = newsService.find(id);
		model.addObject("news", news);
		model.addObject("title", news.getTitle());
		model.addObject("tags", news.getTags().split(","));
		model.setViewName("home/news/detail");
		//查看数加1
		newsService.updateViewNumber(id);
		return model;
	}
	
	/**
	 * 按照分类显示新闻列表
	 * @param model
	 * @param cid
	 * @return
	 */
	@RequestMapping(value="/category_list",method=RequestMethod.GET)
	public ModelAndView categoryList(ModelAndView model,
			@RequestParam(name="cid",required=true) Long cid,
			Page page
			){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 10);
		queryMap.put("categoryId", cid);
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		model.addObject("newsList", newsService.findList(queryMap));
		NewsCategory newsCategory = newsCategoryService.find(cid);
		model.addObject("newsCategory", newsCategory);
		model.addObject("title", newsCategory.getName() + "分类下的新闻信息");
		model.setViewName("home/news/category_list");
		return model;
	}
	
	/**
	 * 获取按评论数排序的最新n条信息
	 * @return
	 */
	@RequestMapping(value="/last_comment_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> lastCommentList(){
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("type", "success");
		ret.put("newsList", newsService.findLastCommentList(10));
		return ret;
	}
	
	
	/**
	 * 分页获取某个分类下的文章
	 * @param page
	 * @param cid
	 * @return
	 */
	@RequestMapping(value="/get_category_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCategoryList(Page page,
			@RequestParam(name="cid",required=true) Long cid
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("categoryId", cid);
		ret.put("type", "success");
		ret.put("newsList", newsService.findList(queryMap));
		return ret;
	}
	
	/**
	 * 获取搜索列表
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
		model.addObject("newsCategoryList", newsCategoryService.findAll());
		model.addObject("newsList", newsService.findList(queryMap));
		model.addObject("title", keyword + "关键字下的新闻信息");
		model.addObject("keyword", keyword);
		model.setViewName("home/news/search_list");
		return model;
	}
	
	/**
	 * 分页加载搜索列表
	 * @param page
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value="/get_search_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSearchList(Page page,
			@RequestParam(name="keyword",required=false,defaultValue="") String keyword
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("title", keyword);
		ret.put("type", "success");
		ret.put("newsList", newsService.findList(queryMap));
		return ret;
	}
	
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="/comment_news",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> commentNews(Comment comment){
		Map<String, Object> ret = new HashMap<String, Object>();
		if(comment == null){
			ret.put("type", "error");
			ret.put("msg", "请填写完整的评论信息！");
			return ret;
		}
		if(comment.getNewsId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择一个文章进行评论！");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getNickname())){
			ret.put("type", "error");
			ret.put("msg", "请填写昵称！");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getContent())){
			ret.put("type", "error");
			ret.put("msg", "请填写评论内容！");
			return ret;
		}
		comment.setCreateTime(new Date());
		if(commentService.add(comment) <= 0){
			ret.put("type", "error");
			ret.put("msg", "评论失败，请联系管理员！");
			return ret;
		}
		//文章评论数加1
		newsService.updateCommentNumber(comment.getNewsId());
		ret.put("type", "success");
		ret.put("createTime", comment.getCreateTime());
		return ret;
	}
	
	/**
	 * 分页获取某一文章的评论
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/get_comment_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCommentList(Page page,Long newsId){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("newsId", newsId);
		ret.put("type", "success");
		ret.put("commentList", commentService.findList(queryMap));
		return ret;
	}
}
