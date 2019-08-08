package com.java.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.java.pojo.Shopcar;

public interface ShopCarService {
	
	//将商品加入购物车
	void addShopCar(HttpSession session, int id, int number);
	
	//查询出该用户在数据库中的所有购物车商品
	List<Shopcar> findAllShopCars(HttpSession session);

	//实现商品加减法
	void updateNumber(HttpSession session,int id,int number);

	//根据 商品id 删除商品
	void deleteShopcar(HttpSession session, int id);

}
