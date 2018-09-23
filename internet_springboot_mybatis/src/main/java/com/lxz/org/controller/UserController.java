package com.lxz.org.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lxz.org.pojo.UserBean;
import com.lxz.org.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	public UserService userService;
	
	
	@ResponseBody
	@RequestMapping(value="/getId{id}",produces= {"application/json;charset=UTF-8"})
	public UserBean getUser(@PathVariable int id) {
	UserBean user=userService.getUser(id);
		return user;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String role() {
		
		return "user";
	}
}
