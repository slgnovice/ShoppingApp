package com.java.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.java.pojo.User;

/**
 * UserMapper 数据访问类
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-07-16 15:19:37
 * @version 1.0
 */
public interface UserMapper {
	
	@Select("select * from ec_user where LOGIN_NAME=#{loginName} and password=#{password}")
	User findUserById(User user);

	//异步校验
	@Select("select * from ec_user where login_name = #{loginName}")
	User findUserByLoginName(String loginName);
	
	//注册用户
	@Insert("insert into ec_user(login_name,password,name,sex,email,phone,address,role,create_date,active) values(#{loginName},#{password},#{name},#{sex},#{email},#{phone},#{address},#{role},#{createDate},#{active})")
//	void insertUser(@Param("login_name") String login_name,@Param("password") String password,@Param("name") String name,@Param("sex") int sex,@Param("email")String email,@Param("phone")String phone,@Param("address")String address,@Param("role")int role,@Param("create_date")Date create_date,@Param("disabled") String disabled,@Param("active")String active);
	void insertUser(User user);

	//激活用户
	@Update("update ec_user set disabled=1,active=''  where  active=#{activeCode}")
	void active(String activeCode);

	//寻找需要激活的用户
	@Select("select * from ec_user where active=#{activeCode}")
	User findUserByActive(String activeCode);
	
}