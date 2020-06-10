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
import com.ischoolbar.programmer.entity.teacher.Department;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.teacher.AcademyService;
import com.ischoolbar.programmer.service.teacher.DepartmentService;
import com.ischoolbar.programmer.service.teacher.TeacherService;

/**
 * רҵ��Ϣ������
 * @author 20161
 *
 */
@RequestMapping("/admin/department")
@Controller
public class DepartmentController {
	@Autowired
	private AcademyService academyService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * ��ʦרҵ�б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String,Object> queryMap = new HashMap<String, Object>();
		model.addObject("academyList", academyService.findAll());
//		model.addObject("departmentList", departmentService.findList(queryMap));
		model.setViewName("department/list");
		return model;
	}
	/**
	 * ��ȡרҵ�б�
	 * @param page
	 * @param departmentName
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDepartmentList(Page page,
			@RequestParam(name="departmentName",required=false,defaultValue="") String departmentName,
			@RequestParam(name="academyId",required=false) Long academyId
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		queryMap.put("academyId", academyId);
		queryMap.put("departmentName", departmentName);
		List<Department> findList = departmentService.findList(queryMap);
		ret.put("rows", findList);
		ret.put("total", departmentService.getTotal(queryMap));
		return ret;
	}
	/**
	 * רҵ����
	 * @param academy
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Department department){
		Map<String, String> ret = new HashMap<String, String>();
		if(department == null){
			ret.put("type", "error");
			ret.put("msg", "����д��ȷ��רҵ��Ϣ!");
			return ret;
		}
		if(StringUtils.isEmpty(department.getDepartmentName())){
			ret.put("type", "error");
			ret.put("msg", "����дרҵ����!");
			return ret;
		}
		if(departmentService.add(department) <= 0){
			ret.put("type", "error");
			ret.put("msg", "����ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "���ӳɹ�!");
		return ret;
	}
	
	/**
	 * רҵ�޸�
	 * @param department
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Department department){
		Map<String, String> ret = new HashMap<String, String>();
		if(department == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ����ȷ��רҵ��Ϣ!");
			return ret;
		}
		if(StringUtils.isEmpty(department.getDepartmentName())){
			ret.put("type", "error");
			ret.put("msg", "����дרҵ����!");
			return ret;
		}
		if(departmentService.edit(department) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ�!");
		return ret;
	}
	
	/**
	 * ɾ��רҵ��Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(String ids){
		Map<String,String> ret = new HashMap<String, String>();
		if(ids == null){
			ret.put("type", "error");
			ret.put("msg", "��ѡ��Ҫɾ����רҵ��Ϣ��");
			return ret;
		}
		if(ids.contains(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		if(departmentService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "רҵɾ��ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ���");
		return ret;
	}
}