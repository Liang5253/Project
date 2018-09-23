package com.lxz.org.controller;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lxz.org.pojo.UserBean;
import com.lxz.org.service.UserService;

@Configuration
public class MainController {
	
	public static  void main (String[] args){
		System.out.println("入口开始！");
		ApplicationContext app=new ClassPathXmlApplicationContext("config/application.xml");
		UserService userService=(UserService) app.getBean("userService");
		/*
		 * 
		UserBean user=null;
		user=new UserBean();
		try {
			user.setUserBrithday(new SimpleDateFormat("yyyy-MM-dd").parse("2011-12-12"));
		} catch (ParseException e) {
			System.err.println("日期格式错误");
		}
		user.setUserEmail("12345678@qqwe.com");
		user.setUserMobile("1871212121");
		user.setUserName("小红");
		user.setUserSex("女");
		user.setUserNote("吃货一个！");
		int i=main.userService.insertUser(user);
		role=main.roleService.getRole(1);
		 * 
		 * 
		 * */
		long start=System.currentTimeMillis();
		List<UserBean> list=userService.findUsers("小");
		long end=System.currentTimeMillis();
		System.out.println(end-start);
//		System.out.println(i>0?"成功":"失败");
		for (UserBean userBean : list) {
			System.out.println(userBean.toString());
		}
	}
}
