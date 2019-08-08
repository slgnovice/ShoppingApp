package com.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.mapper.ArticleMapper;
import com.java.mapper.ArticleTypeMapper;
import com.java.pojo.Article;
import com.java.pojo.ArticleType;
import com.java.service.ArticleService;
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired(required=true)
	ArticleTypeMapper articleTypeMapper;
	
	@Autowired(required=true)
	ArticleMapper  articleMapper;

	
	//查询一级分类
	@Override
	public List<ArticleType> findFristTypes() {
		// TODO Auto-generated method stub
		List<ArticleType> articleTypes  = articleTypeMapper.findFristTypes();
		return articleTypes;
	}
	
	//3种查询情况
	@Override
	public List<Article> findAllArticles(String typeCode,String  keyword) {
		// TODO Auto-generated method stub
		List<Article>  articles= articleMapper.findAllArticles(typeCode,keyword);
		return articles;
	}

	//查询出二级目录
	@Override
	public List<ArticleType> findAllSecodeType(String typeCode) {
		List<ArticleType> articleSecondeTypes  = articleTypeMapper.findSecondeTypes(typeCode);		
		return articleSecondeTypes;
	}

	//展示商品详情
	@Override
	public Article findIdArticle(Integer id) {
		Article  article =articleMapper.findIdArticle(id);
		return article;
	}

	

}
