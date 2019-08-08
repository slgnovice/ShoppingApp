package com.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.java.pojo.Shopcar;

/**
 * ShopcarMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-07-16 15:19:37
 * @version 1.0
 */
public interface ShopcarMapper {
	
	//通过用户id和商品id查询数据库，判断购物车是否存在该商品
	@Select("select * from ec_shopcar where user_id=#{userId} and article_id=#{id}")
	Shopcar  getShopCarByUserIdAndArticleId(@Param("userId")int userId,@Param("id")int id);

	//若存在，增加数量
	@Update("update ec_shopcar set buynum=#{number}+buynum where user_id=#{userId} and article_id=#{id}")
	void updateShopCar(@Param("userId")int userId,@Param("id")int id,@Param("number")int number);
	
	//若不存在，加入购物车
	@Insert("insert into ec_shopcar(buynum,user_id,article_id)  values(#{number},#{userId},#{id})")
	void insertShopCar(@Param("userId") int userId,@Param("id") int id,@Param("number") int number);
	
	//展示购物车
	List<Shopcar> findAllshopCars(int userId);

	
	//实现商品的加减法
	@Update("update ec_shopcar set buynum=#{number} where user_id=#{userId} and article_id=#{id}")
	void updateNUmber(@Param("userId")int userId,@Param("id")int id,@Param("number")int number);

	//根据用户id 和商品id ，删除商品
	@Delete("delete from ec_shopcar where user_id=#{userId} and article_id=#{id}")
	void deleteshopCarById(@Param("userId")int userId,@Param("id")int id);

}