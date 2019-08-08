package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Order;
import com.java.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService  orderService;
	
	//提交订单操作
	@RequestMapping("/orderSubmit.action")
	public String orderSubmit(String orderInfo,HttpSession session) {
		//提交订单
		try {
			orderService.orderSubmit(orderInfo,session);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//跳转到展示订单详情的方法
		return "redirect:/order/showOrder.action";
	}
	
	//展示订单列表详情
	@RequestMapping("/showOrder.action")
	public String showOrder(Model model,HttpSession session) {
		
		try {
			//根据当前用户的 id 查询出这个用户的所有订单信息
			List<Order> orders = orderService.getOrderByUserId(session);
			//展示订单信息
			model.addAttribute("orders",orders);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//跳转到订单详情页面
		return "order";
	}
}
