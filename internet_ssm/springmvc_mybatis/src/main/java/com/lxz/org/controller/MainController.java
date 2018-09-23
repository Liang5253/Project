package com.lxz.org.controller;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxz.org.pojo.RoleBean;
import com.lxz.org.service.RoleService;
import com.lxz.org.service.impl.RoleServiceImpl;

@Controller
public class MainController {
	
	@Resource
	private RoleService roleService;
	
	@RequestMapping("/role")
	public  String hello() {
		
		return "hello";
	}
	
	@Test
	public void test1() {
		RoleBean role=null;
		long start=System.currentTimeMillis();
		this.roleService=new RoleServiceImpl();
		role=this.roleService.getRole(1);
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		System.out.println(role);
	}
}
