package com.java.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.java.pojo.Order;

public interface OrderService {

	//提交订单
	void orderSubmit(String orderInfo, HttpSession session);

	//展示订单页面
	List<Order> getOrderByUserId(HttpSession session);


}
