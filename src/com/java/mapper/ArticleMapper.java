package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.java.pojo.Article;

/**
 * ArticleMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-07-16 15:19:37
 * @version 1.0
 */
public interface ArticleMapper {
	//查询所有商品
	List<Article> findAllArticles(@Param("typeCode")String typeCode, @Param("keyword")String keyword);
	
	//根据商品id查询出商品信息
	@Select("select * from ec_article where id=#{id}")
	Article findIdArticle(Integer id);

}