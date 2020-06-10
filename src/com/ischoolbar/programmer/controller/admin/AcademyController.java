package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.teacher.Academy;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.teacher.AcademyService;
import com.ischoolbar.programmer.service.teacher.DepartmentService;
import com.ischoolbar.programmer.service.teacher.TeacherService;
/**
 * ѧԺ��Ϣ������
 * @author 20161
 *
 */
@RequestMapping("/admin/academy")
@Controller
public class AcademyController {
	@Autowired
	private AcademyService academyService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * ��ʦѧԺ�б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
//		model.addObject("academyList", academyService.findAll());
//		model.addObject("departmentList", departmentService.findAll());
		model.setViewName("academy/list");
		return model;
	}
	
	/**
	 * ��ȡѧԺ�б�
	 * @param page
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getAcademyList(Page page,
			@RequestParam(name="academyName",required=false,defaultValue="") String academyName
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("academyName", academyName);
		List<Academy> findList = academyService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", academyService.getTotal(queryMap));
		return ret;
	}
	/**
	 * ѧԺ���
	 * @param academy
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Academy academy){
		Map<String, String> ret = new HashMap<String, String>();
		if(academy == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��ѧԺ��Ϣ!");
			return ret;
		}
		if(StringUtils.isEmpty(academy.getAcademyName())){
			ret.put("type", "error");
			ret.put("msg", "����дѧԺ����!");
			return ret;
		}
		if(academyService.add(academy) <= 0){
			ret.put("type", "error");
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�!");
		return ret;
	}
	
	/**
	 * ѧԺ�޸�
	 * @param academy
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Academy academy){
		Map<String, String> ret = new HashMap<String, String>();
		if(academy == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ����ȷ��ѧԺ��Ϣ!");
			return ret;
		}
		if(StringUtils.isEmpty(academy.getAcademyName())){
			ret.put("type", "error");
			ret.put("msg", "����дѧԺ����!");
			return ret;
		}
		if(academyService.edit(academy) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ�!");
		return ret;
	}
	
	/**
	 * ɾ��ѧԺ��Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(Long id){
		Map<String,String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ����ѧԺ��Ϣ��");
			return ret;
		}
		try{
			if(academyService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "ѧԺɾ��ʧ�ܣ�����ϵ����Ա��");
				return ret;
			}
		}catch(Exception e){
			ret.put("type", "error");
			ret.put("msg", "�÷�������ѧԺ��Ϣ������ɾ����");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ���");
		return ret;
	}
	

}
