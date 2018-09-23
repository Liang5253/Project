package com.lxz.org.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lxz.org.pojo.CursorResult;
import com.lxz.org.pojo.Emp;
import com.lxz.org.service.EmpService;

@RestController
@RequestMapping(value="/emp")
public class EmpController {
	
	
	@Resource
	private EmpService empService;
	
	
	@RequestMapping(value="/getall")
	public List<Emp> getall() {
		List<Emp> list=empService.getAll();
		
		return list;
	}
	
	@RequestMapping(value="/find{id}")
	public Object find(@PathVariable int id) {
		List<Emp> list=empService.findEmp("sal", id, 5);
		return list;
	}
	
	@RequestMapping(value="/cursor{pagenum},{pageSize}")
	public Object find(@PathVariable Integer pagenum,@PathVariable Integer pageSize) {
		CursorResult cursorResult=new CursorResult();
		cursorResult.setStart(pagenum);
		cursorResult.setEnd(pageSize);
		cursorResult.setCount(0);
		List<Emp> list=new ArrayList<Emp>();
		cursorResult.setEmpList(list);
		empService.findEmp1(cursorResult);
		Map<String, Object> map=new HashMap<String,Object>();
			map.put("当前页面显示最大条数", pageSize);
			map.put("当前页", pagenum);
			map.put("总页数", cursorResult.getCount()%pageSize==0?cursorResult.getCount()/pageSize:cursorResult.getCount()/pageSize+1);
			map.put("成员信息", cursorResult.getEmpList());
		return map;
	}
}
