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
 * 学院信息控制器
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
	 * 导师学院列表页面
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
	 * 获取学院列表
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
	 * 学院添加
	 * @param academy
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Academy academy){
		Map<String, String> ret = new HashMap<String, String>();
		if(academy == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的学院信息!");
			return ret;
		}
		if(StringUtils.isEmpty(academy.getAcademyName())){
			ret.put("type", "error");
			ret.put("msg", "请填写学院名称!");
			return ret;
		}
		if(academyService.add(academy) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		return ret;
	}
	
	/**
	 * 学院修改
	 * @param academy
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Academy academy){
		Map<String, String> ret = new HashMap<String, String>();
		if(academy == null){
			ret.put("type", "error");
			ret.put("msg", "请选择正确的学院信息!");
			return ret;
		}
		if(StringUtils.isEmpty(academy.getAcademyName())){
			ret.put("type", "error");
			ret.put("msg", "请填写学院名称!");
			return ret;
		}
		if(academyService.edit(academy) <= 0){
			ret.put("type", "error");
			ret.put("msg", "修改失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "修改成功!");
		return ret;
	}
	
	/**
	 * 删除学院信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delete(Long id){
		Map<String,String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的学院信息！");
			return ret;
		}
		try{
			if(academyService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "学院删除失败，请联系管理员！");
				return ret;
			}
		}catch(Exception e){
			ret.put("type", "error");
			ret.put("msg", "该分类下有学院信息，不可删除！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	

}
