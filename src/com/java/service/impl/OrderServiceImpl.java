package com.java.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.mapper.OrderItemMapper;
import com.java.mapper.OrderMapper;
import com.java.pojo.Order;
import com.java.pojo.OrderItem;
import com.java.pojo.User;
import com.java.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	//提交订单
	@Override
	public void orderSubmit(String orderInfo, HttpSession session) {
		// TODO Auto-generated method stub
		//第一步：切分处理从购物车提交来的数据
		String[] orderInfos=orderInfo.substring(1).split("#");
		
		//第二步：封装订单对象,orderCode,createDate,amount总金额，userid都是我们需要获取封装订单的数据
		Order order=new Order();
		//指定下单时间
		order.setCreateDate(new Date());  
		
		User user=(User) session.getAttribute("session_user");
		int userId=user.getId();
		order.setUserId(userId);
		
		// 装订单编号orderCode，格式：PO-当前时间+用户id
		SimpleDateFormat  sdf=new SimpleDateFormat ("yyyyMMddHHmmss");
		
		//设置订单编号
		order.setOrderCode("PO-"+sdf.format(new Date())+userId);
		
		//定义订单编号
		double totalPrice=0.0;
		
		//第三步：创建集合封装订单详情对象。与订单对象是多对一关系
		List<OrderItem> items=new ArrayList<>();
		
		//循环切分购物车中传过来的orderinfo数据
		for(String info:orderInfos) {
			//将商品id ，商品数量，商品总价切分 #1_2_216.0#5_1_158.4
			String[] infos=info.split("_");
			
			//获取商品ID
			int articleId=Integer.valueOf(infos[0]);
			
			//购买数量
			int buyNum=Integer.valueOf(infos[1]);
			
			OrderItem item=new OrderItem();
			item.setArticleId(articleId);
			item.setOrderNum(buyNum);
			
			//订单详情记录放在集合中
			items.add(item);
			//计算订单详情总价
			totalPrice+=Double.valueOf(infos[2]);
		}
		
		//指定订单的总金额，也就是所有订单详情的总价
		order.setAmount(totalPrice);
		
		//保存订单信息，保存完订单信息之后，需要获取订单的id， 因为需要订单的id 存放在订单详情中
		orderMapper.saveOrder(order);
		
		//获取订单主键的 值
		int orderId=order.getId();
		//并将主键设置到订单详情对象中
		for(OrderItem item:items) {
			item.setOrderId(orderId);
			
			//保存订单详情对象
			orderItemMapper.saveItem(item);
			
		}
		
	}

	//展示订单详情页面
	@Override
	public List<Order> getOrderByUserId(HttpSession session) {
		// TODO Auto-generated method stub
		//获取登录当前用户的Id
		User user=(User) session.getAttribute("session_user");
		int userId= user.getId();
		
		//通过用户id查询出所有的订单
		return orderMapper.getOrderByUserId(userId);
	
	}

}
