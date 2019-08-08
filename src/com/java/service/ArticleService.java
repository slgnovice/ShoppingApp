package com.java.service;

import java.util.List;

import com.java.pojo.Article;
import com.java.pojo.ArticleType;


public interface ArticleService {
	//查询出所有一级目录
	public List<ArticleType> findFristTypes();
	
	//3种情况查询。一级、关键字、无查询
	public List<Article> findAllArticles(String typeCode, String  keyword);

	//根据一级目录，带出二级目录
	public List<ArticleType> findAllSecodeType(String typeCode);

	//根据id查询商品信息
	public Article findIdArticle(Integer id);
}
