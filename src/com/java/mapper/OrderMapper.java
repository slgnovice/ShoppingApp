package com.java.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.java.pojo.Order;

/**
 * OrderMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-07-16 15:19:37
 * @version 1.0
 */
public interface OrderMapper {

	//提交订单
	void saveOrder(Order order);
	
	//展示订单页面
	List<Order> getOrderByUserId(int userId);
	

}