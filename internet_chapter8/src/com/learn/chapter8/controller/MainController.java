package com.learn.chapter8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learn.chapter8.service.RoleService;

@Controller
public class MainController {
	
	@Autowired
	private RoleService roleService=null;
	
	@RequestMapping("/role")
	public  String hello() {
//		RoleBean role=null;
//		long start=System.currentTimeMillis();
//		role=this.roleService.getRole(1);
//		long end=System.currentTimeMillis();
//		System.out.println(end-start);
//		System.out.println(role);
		return "hello";
	}
}
