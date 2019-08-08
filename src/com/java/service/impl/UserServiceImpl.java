package com.java.service.impl;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;


import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


import com.java.mapper.UserMapper;
import com.java.pojo.User;
import com.java.service.UserService;
import com.sun.mail.smtp.SMTPMessage;


@Service
@Controller
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public User findUserById(User user) {
		// TODO Auto-generated method stub
		User user1=userMapper.findUserById(user);
		return user1;
	}

	//异步校验账户是否存在
	@Override
	public String lidLoginName(String loginName) {
		// TODO Auto-generated method stub
		User user=userMapper.findUserByLoginName(loginName);
		if(user!=null) {
			//不为空， 证明
			return "账户已存在";
		}
		return "";
	}
	

//	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

//	@Resource
//	JavaMailSender mailSender;
	
//	@Resource
//	SimpleMailMessage simpleMailMessage;
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		try {
			
			//生成随机激活码
			String active=UUID.randomUUID().toString();
			//生成日期时间
			user.setCreateDate(new Date());
			user.setActive(active);
			//新增用户
			userMapper.insertUser(user);
			
			
			//开始发送邮件给用户
			//创建Properties对象，用来封装邮件服务器相关信息
			Properties props = new Properties();
			//设置邮件服务器的地址
			props.setProperty("mail.smtp.host", "smtp.qq.com");
			props.put("mail.smtp.port", 25);
			//邮件服务器需要权限，指定用户必须登录邮件服务器才能发送邮件
			props.setProperty("mail.smtp.auth", "true");
			
			
			//创建Authenticator的实例，实现账户、密码的鉴权。
			
	        Authenticator auth = new Authenticator(){
	        	@Override
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication("1758750759@qq.com", "coetxqpwamgufbec");
	            }
	        };
		
			//通过Session与服务器建立连接
			Session session = Session.getInstance(props,auth);
				
			//创建发送邮件对象，该对象主要用于封装邮件相关信息，比如  主题  发件人  邮件内容等
			SMTPMessage message = new SMTPMessage(session);
					
			//设置邮件的主题
//			message.setSubject("用户注册激活邮件，请勿回复，按照指引激活");
			message.setSubject("Tencent公司1000万大奖等你拿，期待您的加入......");
			//设置邮件的内容
			message.setContent("<a href='http://127.0.0.1:8080/ShoppingApp/user/active?activeCode="+user.getActive()+"' target='_blank'>恭喜郝先生，您是全球最靓的崽，砸中了Tencent公司1000万的金蛋，请于2020年2月30日前及时领取！！！！</a>", "text/html;charset=UTF-8");
			
			message.setFrom(new InternetAddress("1758750759@qq.com","北海道"));
			
			//设置发件人
//			message.setFrom(new InternetAddress("1758750759@qq.com"));
			
			//设置收件人   接收者类型由：TO(收件人)、CC(抄送)、BCC(密送)
			message.setRecipient(RecipientType.BCC, new InternetAddress(user.getEmail()));
				
			//发送邮件
			
			Transport.send(message);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
	}
		
			

	//激活用户
	@Override
	public String activeUser(String activeCode) {
		// TODO Auto-generated method stub
		//第一步：先找到需要的对象是谁
		User user = userMapper.findUserByActive(activeCode);
		//第二步：激活已经找到的用户
		if(user!=null) {
			userMapper.active(activeCode);
			return "";
		}
		return "激活失败";
	}
	
	
	
}
