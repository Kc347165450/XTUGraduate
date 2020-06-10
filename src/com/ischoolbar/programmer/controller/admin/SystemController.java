package com.ischoolbar.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Authority;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.entity.admin.Role;
import com.ischoolbar.programmer.entity.admin.User;
import com.ischoolbar.programmer.service.admin.AuthorityService;
import com.ischoolbar.programmer.service.admin.LogService;
import com.ischoolbar.programmer.service.admin.MenuService;
import com.ischoolbar.programmer.service.admin.RoleService;
import com.ischoolbar.programmer.service.admin.UserService;
import com.ischoolbar.programmer.service.forum.TopicService;
import com.ischoolbar.programmer.service.forum.impl.TopicServiceImpl;
import com.ischoolbar.programmer.util.CpachaUtil;
import com.ischoolbar.programmer.util.MailUtil;
import com.ischoolbar.programmer.util.MenuUtil;
import com.ischoolbar.programmer.util.ProduceMD5;

/**
 * ϵͳ�����������
 * @author llq
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
    public TopicService topicService;
	
	/**
	 * ϵͳ��¼�����ҳ
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model,HttpServletRequest request){
		List<Menu> userMenus = (List<Menu>)request.getSession().getAttribute("userMenus");
		model.addObject("topMenuList", MenuUtil.getAllTopMenu(userMenus));
		model.addObject("secondMenuList", MenuUtil.getAllSecondMenu(userMenus));
		model.setViewName("system/index");
		return model;//WEB-INF/views/+system/index+.jsp = WEB-INF/views/system/index.jsp
	}
	
	/**
	 * ϵͳ��¼��Ļ�ӭҳ
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView welcome(ModelAndView model){
		model.setViewName("system/welcome");
		return model;
	}
	/**
	 * ����Ա��½ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminlogin",method=RequestMethod.GET)
	public ModelAndView adminlogin(ModelAndView model){
		model.setViewName("system/adminlogin");
		return model;
	}
	
	/**
	 * ����Ա��¼���ύ���������
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/adminlogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> adminloginAct(User user,String cpacha,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "����д�û���Ϣ��");
			return ret;
		}
		//String password=ProduceMD5.getMD5(user.getPassword());
		if(StringUtils.isEmpty(cpacha)){
			ret.put("type", "error");
			ret.put("msg", "����д��֤�룡");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "����д�û�����");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "����д���룡");
			return ret;
		}
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null){
			ret.put("type", "error");
			ret.put("msg", "�Ự��ʱ����ˢ��ҳ�棡");
			return ret;
		}
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "��֤�����");
			logService.add("�û���Ϊ"+user.getUsername()+"���û���¼ʱ������֤�����!");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername == null){
			ret.put("type", "error");
			ret.put("msg", "���û��������ڣ�");
			logService.add("��¼ʱ���û���Ϊ"+user.getUsername()+"���û�������!");
			return ret;
		}
		if(!user.getPassword().equals(findByUsername.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "�������");
			logService.add("�û���Ϊ"+user.getUsername()+"���û���¼ʱ�����������!");
			return ret;
		}
		if(findByUsername.getRoleId() == 2){
			ret.put("type", "error");
			ret.put("msg", "��̨���޹���Ա��½��");
			return ret;
		}
		//˵���û������뼰��֤�붼��ȷ
		//��ʱ��Ҫ��ѯ�û��Ľ�ɫȨ��
		Role role = roleService.find(findByUsername.getRoleId());
		List<Authority> authorityList = authorityService.findListByRoleId(role.getId());//���ݽ�ɫ��ȡȨ���б�
		String menuIds = "";
		for(Authority authority:authorityList){
			menuIds += authority.getMenuId() + ",";
		}
		if(!StringUtils.isEmpty(menuIds)){
			menuIds = menuIds.substring(0,menuIds.length()-1);
		}
		List<Menu> userMenus = menuService.findListByIds(menuIds);
		//�ѽ�ɫ��Ϣ���˵���Ϣ�ŵ�session��
		request.getSession().setAttribute("admin", findByUsername);
		request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userMenus", userMenus);
		ret.put("type", "success");
		ret.put("msg", "��¼�ɹ���");
		logService.add("�û���Ϊ{"+user.getUsername()+"}����ɫΪ{"+role.getName()+"}���û���¼�ɹ�!");
		return ret;
	}
	
	/**
	 * ע��ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register(ModelAndView model){
		model.setViewName("system/register");
		return model;
	}
	/**
	 * ����������֤��
	 * @param email
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sendEmail",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> sendEmail(String email,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		MailUtil mailUtil = new MailUtil();
		int code = (int) ((Math.random() * 9 + 1) * 100000);
		String emailCode = Integer.toString(code);
		mailUtil.sendMail(email, emailCode);//���շ�  ������
		request.getSession().setAttribute("code", emailCode);
		request.getSession().setMaxInactiveInterval(15*60);//��֤��15���Ӻ����
		ret.put("type", "success");
		return ret;
	}
	
	/**
	 * ע����ύ���������
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> registerAct(User user,String emailCode,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "����д�û���Ϣ��");
			return ret;
		}
		User findByEmail = userService.findByEmail(user.getEmail());
		if(findByEmail != null){
			ret.put("type", "error");
			ret.put("msg", "ÿ������ֻ��ע��һλ�û���");
			return ret;
		}
		String code = (String) request.getSession().getAttribute("code");
		if (!emailCode.equals(code)) {
			ret.put("type", "error");
			ret.put("msg", "��֤�����");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername != null){
			ret.put("type", "error");
			ret.put("msg", "���û����Ѵ��ڣ�");
			return ret;
		}
		User findByNickname = userService.findByNickname(user.getNickname());
		if(findByNickname != null){
			ret.put("type", "error");
			ret.put("msg", "���ǳ��Ѵ��ڣ�");
			return ret;
		}
		//˵���û������뼰��֤�붼��ȷ
		user.setPassword(ProduceMD5.getMD5(user.getPassword()));//�û��������
		userService.register(user);
		ret.put("type", "success");
		ret.put("msg", "ע��ɹ���");
		logService.add("�û���Ϊ{"+user.getUsername()+"}���û�ע��ɹ�!");						
		return ret;
		
	}
	
	/**
	 * �û���½ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/userlogin",method=RequestMethod.GET)
	public ModelAndView userlogin(ModelAndView model){
		model.setViewName("system/userlogin");
		return model;
	}
	
	/**
	 * �û���¼���ύ���������
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/userlogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> userloginAct(User user,String cpacha,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "����д�û���Ϣ��");
			return ret;
		}
		String password=ProduceMD5.getMD5(user.getPassword());
		if(StringUtils.isEmpty(cpacha)){
			ret.put("type", "error");
			ret.put("msg", "����д��֤�룡");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "����д�û�����");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "����д���룡");
			return ret;
		}
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null){
			ret.put("type", "error");
			ret.put("msg", "�Ự��ʱ����ˢ��ҳ�棡");
			return ret;
		}
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "��֤�����");
			logService.add("�û���Ϊ"+user.getUsername()+"���û���¼ʱ������֤�����!");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername == null){
			ret.put("type", "error");
			ret.put("msg", "���û��������ڣ�");
			logService.add("��¼ʱ���û���Ϊ"+user.getUsername()+"���û�������!");
			return ret;
		}
		if(!password.equals(findByUsername.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "�������");
			logService.add("�û���Ϊ"+user.getUsername()+"���û���¼ʱ�����������!");
			return ret;
		}
		//˵���û������뼰��֤�붼��ȷ
		//��ʱ��Ҫ��ѯ�û��Ľ�ɫȨ��
		Role role = roleService.find(findByUsername.getRoleId());
		List<Authority> authorityList = authorityService.findListByRoleId(role.getId());//���ݽ�ɫ��ȡȨ���б�
		String menuIds = "";
		for(Authority authority:authorityList){
			menuIds += authority.getMenuId() + ",";
		}
		if(!StringUtils.isEmpty(menuIds)){
			menuIds = menuIds.substring(0,menuIds.length()-1);
		}
		List<Menu> userMenus = menuService.findListByIds(menuIds);
		//�ѽ�ɫ��Ϣ���˵���Ϣ�ŵ�session��
		request.getSession().setAttribute("user", findByUsername);
		request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userMenus", userMenus);
		ret.put("type", "success");
		ret.put("msg", "��¼�ɹ���");
		logService.add("�û���Ϊ{"+user.getUsername()+"}����ɫΪ{"+role.getName()+"}���û���¼�ɹ�!");
		return ret;
	}
	
	
	/**
	 * ��̨�˳�ע������
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("admin", null);
		session.setAttribute("role", null);
		request.getSession().setAttribute("userMenus", null);
		return "redirect:adminlogin";
	}
	
	/**
	 * ǰ̨�˳�ע������
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
	
	/**
	 * �޸�����ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_password",method=RequestMethod.GET)
	public ModelAndView editPassword(ModelAndView model){
		model.setViewName("system/edit_password");
		return model;
	}
	
	@RequestMapping(value="/edit_password",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editPasswordAct(String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(newpassword)){
			ret.put("type", "error");
			ret.put("msg", "����д�����룡");
			return ret;
		}
		User user = (User)request.getSession().getAttribute("admin");
		if(!user.getPassword().equals(oldpassword)){
			ret.put("type", "error");
			ret.put("msg", "ԭ�������");
			return ret;
		}
		user.setPassword(newpassword);
		if(userService.editPassword(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "�����޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�����޸ĳɹ���");
		logService.add("�û���Ϊ{"+user.getUsername()+"}�����û��ɹ��޸�����!");
		return ret;
	} 
	
	/**
	 * ��ϵͳ���е���֤������ô˷���
	 * @param vcodeLen
	 * @param width
	 * @param height
	 * @param cpachaType:����������֤������ͣ������ַ���
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
	public void generateCpacha(
			@RequestParam(name="vl",required=false,defaultValue="4") Integer vcodeLen,
			@RequestParam(name="w",required=false,defaultValue="100") Integer width,
			@RequestParam(name="h",required=false,defaultValue="30") Integer height,
			@RequestParam(name="type",required=true,defaultValue="loginCpacha") String cpachaType,
			HttpServletRequest request,
			HttpServletResponse response){
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute(cpachaType, generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �û���������ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/resetPasswd",method=RequestMethod.GET)
	public ModelAndView resetPasswd(ModelAndView model){
		model.setViewName("system/resetPasswd");
		return model;
	}
	@RequestMapping(value="/resetPasswd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> resetPasswd(User user,String emailCode,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(user == null){
			ret.put("type", "error");
			ret.put("msg", "����д�û���Ϣ��");
			return ret;
		}
		User findByEmail = userService.findByEmail(user.getEmail());
		if(StringUtils.isEmpty(emailCode)){
			ret.put("type", "error");
			ret.put("msg", "����д��֤�룡");
			System.out.println(emailCode);
			return ret;
		}
		if(StringUtils.isEmpty(user.getEmail())){
			ret.put("type", "error");
			ret.put("msg", "����д���䣡");
			return ret;
		}
		if (!user.getEmail().equals(findByEmail.getEmail())) {
			ret.put("type", "error");
			ret.put("msg", "���䲻ƥ�䣡");
			return ret;
		}
		
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "����д���룡");
			return ret;
		}
		String code = (String) request.getSession().getAttribute("code");
		if (!emailCode.equals(code)) {
			ret.put("type", "error");
			ret.put("msg", "��֤�����");
			return ret;
		}
		
		//˵���û������뼰��֤�붼��ȷ
		findByEmail.setPassword(ProduceMD5.getMD5(user.getPassword()));//�û��������
		if(userService.editPassword(findByEmail)<= 0){
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�������óɹ���");
		logService.add("�û���Ϊ{"+user.getUsername()+"}���û���������ɹ�!");						
		return ret;
	}
}
