package com.java.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.java.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	//拦截未登录用户将商品加入购物车
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//从session里面获取登录用户的信息
		User user=(User) request.getSession().getAttribute("session_user");
		
		if (user==null) {
			//未登录，跳转到登录页面
			request.setAttribute("error_message", "请先登录，再将商品加入购物车");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
			
			return false;
		
		}else {
	
			return true;
		}		
		
	}


}
