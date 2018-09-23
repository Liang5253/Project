package com.lxz.org.service;

import java.util.List;

import com.lxz.org.pojo.CursorResult;
import com.lxz.org.pojo.Emp;

public interface EmpService {
	
	public Emp getREmp(Integer id);
	
	public List<Emp> getAll();
	
	public List<Emp> findEmp(String lowName,int start,int end);
	
	public List<Emp> findEmp1(CursorResult cursorResult);
}
