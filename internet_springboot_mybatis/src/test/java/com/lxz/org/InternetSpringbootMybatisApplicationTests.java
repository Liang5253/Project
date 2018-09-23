package com.lxz.org;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lxz.org.pojo.UserBean;
import com.lxz.org.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InternetSpringbootMybatisApplicationTests {

	@Autowired
	private UserService userService;
	
	@Test
	public void contextLoads() throws ParseException {
	UserBean user=new UserBean();
	user.setUserBrithday(new SimpleDateFormat("yyyy-MM-dd").parse("2011-12-12"));
	user.setUserEmail("shw@shw.com");
	user.setUserMobile("13232140645");
	user.setUserName("李四");
	user.setUserNote("吃货一个！");
	user.setUserSex("男");
		int i=this.userService.insertUser(user);
		System.out.println(i>0?"成功":"失败");
		
	}
}
