package com.java.global.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception ex) {
		
		MyException exception1=null;
		
		if (ex instanceof MyException) {
			exception1=(MyException) ex;
		}
		ModelAndView andView=new ModelAndView();
		if (exception1!=null) {
			andView.addObject("errorMessage", exception1.getMessage());
			andView.setViewName("error");
			return andView;
		}
		
		return null;
	}

}
