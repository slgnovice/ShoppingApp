package com.java.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.pojo.User;
import com.java.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//处理登录请求
	@RequestMapping("/userLogin")
	public String userLogin(User user,Model model,HttpSession session) {
		User user1 = userService.findUserById(user);
		
		//判断用户在数据库中是否有记录
		if(user1==null) {
			//返回错误信息
			model.addAttribute("error_message", "请先进行注册，再进行登录操作");
			return "/login";
		}else if(user1.getDisabled().equals("0")){
			model.addAttribute("error_message", "邮箱未激活，请激活后登录");
			return "/login";
		}
		else {
			//待用户保存到域中，并跳转到首页
			session.setAttribute("session_user", user1);
			return "redirect:/article/index";
		}
	} 
	
	@RequestMapping("/logout")
	public String userLogout(HttpSession session) {
		
		//从浏览器移除登录信息
		session.removeAttribute("session_user");
		return "redirect:/article/index";
				
	}
	
	//异步校验用户账户是否存在
	@ResponseBody
	@RequestMapping(value="/validLoginName",produces = {"application/text;charset=utf-8"})
	public String validLoginName(String loginName) {
		
		//校验账户是否存在
		String result = userService.lidLoginName(loginName);
		System.out.println(result);
		return result;
	}
	
	//用户注册
	@RequestMapping("/userRegister")
	public String userRegister(Model model,User user) {
		//注册用户
		try {
			userService.saveUser(user);	
			model.addAttribute("message", "注册成功！！请到邮箱激活账号");
		}catch(Exception e){
			e.printStackTrace();
			model.addAttribute("message", "注册失败，请返回登录页面，重新注册！！");
			
		}	
		return "login";
	}
	
	//激活用户
		@RequestMapping("/active")
		public String active(String activeCode,Model model) {
			try {
				String activeStatus = userService.activeUser(activeCode);
				model.addAttribute("message",!activeStatus.equals("")?activeStatus:"激活成功" );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.addAttribute("message","激活失败" );
			}
		
			return "login";
		}
}
