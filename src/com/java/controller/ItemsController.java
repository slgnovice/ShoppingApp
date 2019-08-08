package com.java.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.global.exception.MyException;
import com.java.pojo.Items;
import com.java.pojo.User;
import com.java.service.ItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {
	
	@RequestMapping("/list")
	public ModelAndView itemListAll(){
		List<Items> itemList = new ArrayList<Items>();
		//商品列表
		Items items_1 = new Items();
		items_1.setName("联想笔记本_3");
		items_1.setPrice(6000f);
		items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

		Items items_2 = new Items();
		items_2.setName("苹果手机");
		items_2.setPrice(5000f);
		items_2.setDetail("iphone6苹果手机！");

		itemList.add(items_1);
		itemList.add(items_2);
		
		ModelAndView andView = new ModelAndView();
		// 他类似于 request.setAttribute()
		andView.addObject("itemList", itemList);
		andView.setViewName("itemList");
		
		return andView;
	}
	
	

}
