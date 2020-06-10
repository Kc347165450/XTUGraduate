package com.ischoolbar.programmer.controller.admin;

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
import com.ischoolbar.programmer.entity.admin.NewsCategory;
import com.ischoolbar.programmer.entity.teacher.TeacherComment;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.CommentService;
import com.ischoolbar.programmer.service.admin.NewsService;
import com.ischoolbar.programmer.service.admin.UserService;
import com.ischoolbar.programmer.service.teacher.TeacherCommentService;
import com.ischoolbar.programmer.service.teacher.TeacherService;

/**
 * ��ʦ���ۿ�����
 * @author llq
 *
 */
@RequestMapping("/admin/teacherComment")
@Controller
public class TeacherCommentController {
	
	@Autowired
	private TeacherCommentService teacherCommentService;
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * ��ʦ�����б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 999);
		model.addObject("teacherList", teacherService.findList(queryMap));
		model.setViewName("teacherComment/list");
		return model;
	}
	
	/**
	 * ��ʦ�������
	 * @param newsCategory
	 * @return
	 */
	/*@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> add(TeacherComment comment){
		Map<String,String> ret = new HashMap<String, String>();
		if(comment == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��������Ϣ��");
			return ret;
		}
		if(comment.getTeacherId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫ���۵����ţ�");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getNickname())){
			ret.put("type", "error");
			ret.put("msg", "�����ǳƲ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getContent())){
			ret.put("type", "error");
			ret.put("msg", "�������ݲ���Ϊ�գ�");
			return ret;
		}
		comment.setCreateTime(new Date());
		if(teacherCommentService.add(comment) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�������ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ���");
		//��������������1
		teacherService.updateCommentNumber(comment.getTeacherId());
		return ret;
	}*/
	
	/**
	 * ����������Ϣ�༭
	 * @param newsCategory
	 * @return
	 */
	/*@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> edit(Comment comment){
		Map<String,String> ret = new HashMap<String, String>();
		if(comment == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��������Ϣ��");
			return ret;
		}
		if(comment.getNewsId() == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫ���۵����ţ�");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getNickname())){
			ret.put("type", "error");
			ret.put("msg", "�����ǳƲ���Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(comment.getContent())){
			ret.put("type", "error");
			ret.put("msg", "�������ݲ���Ϊ�գ�");
			return ret;
		}
		if(commentService.edit(comment) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�����޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ���");
		return ret;
	}*/
	
	/**
	 * ɾ����ʦ����
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(String ids){
		Map<String,String> ret = new HashMap<String, String>();
		if(ids == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ����������Ϣ��");
			return ret;
		}
		if(ids.contains(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		if(teacherCommentService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����ɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ���");
		return ret;
	}
	
	/**
	 * ��ҳģ��������ѯ�б�
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(name="nickname",required=false,defaultValue="") String nickname,
//			@RequestParam(name="content",required=false,defaultValue="") String content,
			Page page
			){
		Map<String,Object> ret = new HashMap<String, Object>();
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("nickname", nickname);
//		queryMap.put("content", content);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", teacherCommentService.findList(queryMap));
		ret.put("total", teacherCommentService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * ��������ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public ModelAndView detail(ModelAndView model,Long id){
		model.addObject("comment", teacherCommentService.find(id));
		model.setViewName("teacherComment/detail");
		return model;
	}
}
