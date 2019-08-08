package com.java.service;

import com.java.pojo.User;

public interface UserService {
	
	//查询是否有这个用户信息
	User findUserById(User user);

	//异步校验用户名
	String lidLoginName(String loginName);

	//保存注册的用户
	void saveUser(User user);

	//激活用户
	String activeUser(String activeCode);


}
