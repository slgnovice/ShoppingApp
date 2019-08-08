package com.java.mapper;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Insert;

import com.java.pojo.Order;
import com.java.pojo.OrderItem;

/**
 * OrderItemMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-07-16 15:19:37
 * @version 1.0
 */
public interface OrderItemMapper {

	@Insert("insert into ec_order_item(order_id,article_id,order_num) values(#{orderId},#{articleId},#{orderNum})")
	void saveItem(OrderItem item);

	
	List<Order> getOrderItemByOrderId(HttpSession session);



}