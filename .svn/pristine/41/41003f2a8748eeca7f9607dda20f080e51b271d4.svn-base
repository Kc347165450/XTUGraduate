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
 * 系统操作类控制器
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
	 * 系统登录后的主页
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
	 * 系统登录后的欢迎页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView welcome(ModelAndView model){
		model.setViewName("system/welcome");
		return model;
	}
	/**
	 * 管理员登陆页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminlogin",method=RequestMethod.GET)
	public ModelAndView adminlogin(ModelAndView model){
		model.setViewName("system/adminlogin");
		return model;
	}
	
	/**
	 * 管理员登录表单提交处理控制器
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
			ret.put("msg", "请填写用户信息！");
			return ret;
		}
		//String password=ProduceMD5.getMD5(user.getPassword());
		if(StringUtils.isEmpty(cpacha)){
			ret.put("type", "error");
			ret.put("msg", "请填写验证码！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null){
			ret.put("type", "error");
			ret.put("msg", "会话超时，请刷新页面！");
			return ret;
		}
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
			logService.add("用户名为"+user.getUsername()+"的用户登录时输入验证码错误!");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername == null){
			ret.put("type", "error");
			ret.put("msg", "该用户名不存在！");
			logService.add("登录时，用户名为"+user.getUsername()+"的用户不存在!");
			return ret;
		}
		if(!user.getPassword().equals(findByUsername.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "密码错误！");
			logService.add("用户名为"+user.getUsername()+"的用户登录时输入密码错误!");
			return ret;
		}
		if(findByUsername.getRoleId() == 2){
			ret.put("type", "error");
			ret.put("msg", "后台仅限管理员登陆！");
			return ret;
		}
		//说明用户名密码及验证码都正确
		//此时需要查询用户的角色权限
		Role role = roleService.find(findByUsername.getRoleId());
		List<Authority> authorityList = authorityService.findListByRoleId(role.getId());//根据角色获取权限列表
		String menuIds = "";
		for(Authority authority:authorityList){
			menuIds += authority.getMenuId() + ",";
		}
		if(!StringUtils.isEmpty(menuIds)){
			menuIds = menuIds.substring(0,menuIds.length()-1);
		}
		List<Menu> userMenus = menuService.findListByIds(menuIds);
		//把角色信息、菜单信息放到session中
		request.getSession().setAttribute("admin", findByUsername);
		request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userMenus", userMenus);
		ret.put("type", "success");
		ret.put("msg", "登录成功！");
		logService.add("用户名为{"+user.getUsername()+"}，角色为{"+role.getName()+"}的用户登录成功!");
		return ret;
	}
	
	/**
	 * 注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register(ModelAndView model){
		model.setViewName("system/register");
		return model;
	}
	/**
	 * 发送邮箱验证码
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
		mailUtil.sendMail(email, emailCode);//接收方  接受码
		request.getSession().setAttribute("code", emailCode);
		request.getSession().setMaxInactiveInterval(15*60);//验证码15分钟后过期
		ret.put("type", "success");
		return ret;
	}
	
	/**
	 * 注册表单提交处理控制器
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
			ret.put("msg", "请填写用户信息！");
			return ret;
		}
		User findByEmail = userService.findByEmail(user.getEmail());
		if(findByEmail != null){
			ret.put("type", "error");
			ret.put("msg", "每个邮箱只能注册一位用户！");
			return ret;
		}
		String code = (String) request.getSession().getAttribute("code");
		if (!emailCode.equals(code)) {
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername != null){
			ret.put("type", "error");
			ret.put("msg", "该用户名已存在！");
			return ret;
		}
		User findByNickname = userService.findByNickname(user.getNickname());
		if(findByNickname != null){
			ret.put("type", "error");
			ret.put("msg", "该昵称已存在！");
			return ret;
		}
		//说明用户名密码及验证码都正确
		user.setPassword(ProduceMD5.getMD5(user.getPassword()));//用户密码加密
		userService.register(user);
		ret.put("type", "success");
		ret.put("msg", "注册成功！");
		logService.add("用户名为{"+user.getUsername()+"}的用户注册成功!");						
		return ret;
		
	}
	
	/**
	 * 用户登陆页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/userlogin",method=RequestMethod.GET)
	public ModelAndView userlogin(ModelAndView model){
		model.setViewName("system/userlogin");
		return model;
	}
	
	/**
	 * 用户登录表单提交处理控制器
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
			ret.put("msg", "请填写用户信息！");
			return ret;
		}
		String password=ProduceMD5.getMD5(user.getPassword());
		if(StringUtils.isEmpty(cpacha)){
			ret.put("type", "error");
			ret.put("msg", "请填写验证码！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null){
			ret.put("type", "error");
			ret.put("msg", "会话超时，请刷新页面！");
			return ret;
		}
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
			logService.add("用户名为"+user.getUsername()+"的用户登录时输入验证码错误!");
			return ret;
		}
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername == null){
			ret.put("type", "error");
			ret.put("msg", "该用户名不存在！");
			logService.add("登录时，用户名为"+user.getUsername()+"的用户不存在!");
			return ret;
		}
		if(!password.equals(findByUsername.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "密码错误！");
			logService.add("用户名为"+user.getUsername()+"的用户登录时输入密码错误!");
			return ret;
		}
		//说明用户名密码及验证码都正确
		//此时需要查询用户的角色权限
		Role role = roleService.find(findByUsername.getRoleId());
		List<Authority> authorityList = authorityService.findListByRoleId(role.getId());//根据角色获取权限列表
		String menuIds = "";
		for(Authority authority:authorityList){
			menuIds += authority.getMenuId() + ",";
		}
		if(!StringUtils.isEmpty(menuIds)){
			menuIds = menuIds.substring(0,menuIds.length()-1);
		}
		List<Menu> userMenus = menuService.findListByIds(menuIds);
		//把角色信息、菜单信息放到session中
		request.getSession().setAttribute("user", findByUsername);
		request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userMenus", userMenus);
		ret.put("type", "success");
		ret.put("msg", "登录成功！");
		logService.add("用户名为{"+user.getUsername()+"}，角色为{"+role.getName()+"}的用户登录成功!");
		return ret;
	}
	
	
	/**
	 * 后台退出注销功能
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
	
	/**
	 * 修改密码页面
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
			ret.put("msg", "请填写新密码！");
			return ret;
		}
		User user = (User)request.getSession().getAttribute("admin");
		if(!user.getPassword().equals(oldpassword)){
			ret.put("type", "error");
			ret.put("msg", "原密码错误！");
			return ret;
		}
		user.setPassword(newpassword);
		if(userService.editPassword(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "密码修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "密码修改成功！");
		logService.add("用户名为{"+user.getUsername()+"}，的用户成功修改密码!");
		return ret;
	} 
	
	/**
	 * 本系统所有的验证码均采用此方法
	 * @param vcodeLen
	 * @param width
	 * @param height
	 * @param cpachaType:用来区别验证码的类型，传入字符串
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
	 * 用户重置密码页面
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
			ret.put("msg", "请填写用户信息！");
			return ret;
		}
		User findByEmail = userService.findByEmail(user.getEmail());
		if(StringUtils.isEmpty(emailCode)){
			ret.put("type", "error");
			ret.put("msg", "请填写验证码！");
			System.out.println(emailCode);
			return ret;
		}
		if(StringUtils.isEmpty(user.getEmail())){
			ret.put("type", "error");
			ret.put("msg", "请填写邮箱！");
			return ret;
		}
		if (!user.getEmail().equals(findByEmail.getEmail())) {
			ret.put("type", "error");
			ret.put("msg", "邮箱不匹配！");
			return ret;
		}
		
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		String code = (String) request.getSession().getAttribute("code");
		if (!emailCode.equals(code)) {
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
			return ret;
		}
		
		//说明用户名密码及验证码都正确
		findByEmail.setPassword(ProduceMD5.getMD5(user.getPassword()));//用户密码加密
		if(userService.editPassword(findByEmail)<= 0){
			ret.put("msg", "修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "密码重置成功！");
		logService.add("用户名为{"+user.getUsername()+"}的用户重置密码成功!");						
		return ret;
	}
}
