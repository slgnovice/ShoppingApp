package com.java.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Shopcar;
import com.java.service.ShopCarService;

@Controller
@RequestMapping("/shopCar")
public class ShopCarController {
	
	@Autowired
	private ShopCarService shopCarService;
	
	@RequestMapping("/addToCar.action")
	public String  addShopCar(HttpSession session,int id,int number) {
		//根据用户信息，商品id，已存在数量来添加商品至购物车
		shopCarService.addShopCar(session, id, number);
		//商品加入购物车成功之后，展示购物车中的商品信息
		return "redirect:/shopCar/showShopCar.action";	
	}
	
	//展示购物车详情页
	@RequestMapping("/showShopCar.action")
	public String showShopCar(HttpSession session,Model model) {
		
		//查询出该用户在数据库中的所有购物车商品
		List<Shopcar> shopcars = shopCarService.findAllShopCars(session);
		model.addAttribute("shopCars", shopcars);
		
		//计算购物车商品总额
		double totalPrice=0.0;
		//遍历购物车商品
		for(Shopcar shopcar:shopcars) {
			totalPrice += shopcar.getArticle().getDiscountPrice()*shopcar.getBuynum();
			model.addAttribute("totalPrice", totalPrice);
		}	
		return "shopCar";	
	}
	
	//实现商品加减法
	@RequestMapping("/updateShopcar.action")
	public String  updateShopcar(HttpSession session,int id,int number) {
		shopCarService.updateNumber(session, id, number);
		return "redirect:/shopCar/showShopCar.action";
	}
	
	//删除商品
	@RequestMapping("/deleteShopCar.action")
	public String deleteShopcar(HttpSession session,int id) {
		shopCarService.deleteShopcar(session,id);
		
		return "redirect:/shopCar/showShopCar.action";
	}
	
}
