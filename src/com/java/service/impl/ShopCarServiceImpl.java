package com.java.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.mapper.ShopcarMapper;
import com.java.pojo.Shopcar;
import com.java.pojo.User;
import com.java.service.ShopCarService;

@Transactional
@Service
public class ShopCarServiceImpl implements ShopCarService {

	@Autowired
	private ShopcarMapper shopcarMapper;
	
	//将商品加入购物车
	@Override
	public void addShopCar(HttpSession session, int id, int number) {
		// TODO Auto-generated method stub

		//从 session 中获取用户信息
		User user=(User) session.getAttribute("session_user");
		
		//获取用户的 id
		int userId=user.getId();
		
		//根据用户id 和 以及商品id搜索数据库，判断该用户的购物车中是否已经存在该商品
		Shopcar shopcar=shopcarMapper.getShopCarByUserIdAndArticleId(userId, id);
		
		if (shopcar!=null) {
			//不为空，证明已经存在于购物车，故只应增加数量
			shopcarMapper.updateShopCar(userId, id, number);
		}else {
			//该商品不存在于购物车中，应当添加该商品
			shopcarMapper.insertShopCar(userId, id, number);
		}
		
	}

	@Override
	public List<Shopcar> findAllShopCars(HttpSession session) {
		//从 session 中获取用户信息
		User user=(User) session.getAttribute("session_user");
						
		//获取用户的 id
		int userId=user.getId();		
		//根据用户id搜索数据库，查询出该用户在数据库中的所有购物车商品
		List<Shopcar> shopcars=shopcarMapper.findAllshopCars(userId);
		
		return shopcars;
	}

	@Override
	public void updateNumber(HttpSession session, int id, int number) {
		// TODO Auto-generated method stub
		//从 session 中获取用户信息
		User user=(User) session.getAttribute("session_user");
				
		//获取用户的 id
		int userId=user.getId();
		shopcarMapper.updateNUmber(userId, id, number);
		
	}

	@Override
	public void deleteShopcar(HttpSession session, int id) {
		//从 session 中获取用户信息
		User user=(User) session.getAttribute("session_user");					
		//获取用户的 id
		int userId=user.getId();
		shopcarMapper.deleteshopCarById(userId,id);
		
	}

}
