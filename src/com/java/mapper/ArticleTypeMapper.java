package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.java.pojo.Article;
import com.java.pojo.ArticleType;

/**
 * ArticleTypeMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-07-16 15:19:37
 * @version 1.0
 */
public interface ArticleTypeMapper {
	//展示首页一级分类
	@Select("select * from ec_article_type where length(CODE)=4")
	List<ArticleType> findFristTypes();
	
	@Select("select * from ec_article_type where code like #{typeCode} and length(CODE)=8")
	List<ArticleType> findSecondeTypes(String typeCode);


}