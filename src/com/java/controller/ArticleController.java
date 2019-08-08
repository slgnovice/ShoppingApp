package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.pojo.Article;
import com.java.pojo.ArticleType;
import com.java.service.ArticleService;


@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired(required=true)
	private ArticleService articleService;
	
	@RequestMapping("/index")
	public String ArticleIndex(Model model,String typeCode,String keyword) {
		//展示首页一级分类
		List<ArticleType> articleTypes= articleService.findFristTypes();
		model.addAttribute("articleTypes", articleTypes);
		
		//点击一级分类，带出二级分类信息
		if(typeCode!=null && !typeCode.equals("")) {
			String code=typeCode.substring(0,4);
			List<ArticleType> secondArticleTypes = articleService.findAllSecodeType(code+'%');
			model.addAttribute("secondArticleTypes", secondArticleTypes);
		}
		
		//展示商品,实现了3种情况，查询所有，查询一级分类，关键字查询
		List<Article> articles=articleService.findAllArticles(typeCode==null?null:typeCode+'%',keyword==null?null:"%"+keyword+'%');
		model.addAttribute("articles", articles);
		
		return "articleIndex";
		
	}
	
	@RequestMapping("/detail")
	public String showDetail(Model model,Integer id) {
		//根据 id查询出商品详情
		Article article = articleService.findIdArticle(id);
		model.addAttribute("article", article);
		return "articleDetail";
		
	}

}
