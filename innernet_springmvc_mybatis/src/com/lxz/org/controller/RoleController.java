package com.lxz.org.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lxz.org.pojo.RoleBean;
import com.lxz.org.service.RoleService;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Resource
	public RoleService roleService;
	
	
	@RequestMapping(value="/getId{id}" , method=RequestMethod.POST)
	public String getId(@PathVariable int id,Model model) {
		RoleBean role=roleService.getRole(id);
		model.addAttribute(role);
		return "role";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String role() {
		
		return "role";
	}
	
	
}
