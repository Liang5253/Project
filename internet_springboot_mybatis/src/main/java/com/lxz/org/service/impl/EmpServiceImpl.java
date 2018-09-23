package com.lxz.org.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lxz.org.dao.EmpDao;
import com.lxz.org.pojo.CursorResult;
import com.lxz.org.pojo.Emp;
import com.lxz.org.service.EmpService;


@Service()
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpDao empDao;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public Emp getREmp(Integer id) {
		return empDao.getEmp(id);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<Emp> getAll() {
		// TODO Auto-generated method stub
		return empDao.getAll();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<Emp> findEmp(String lowName, int pagenum, int sum) {
		// TODO Auto-generated method stub
		
		/**
		 * 	分页查询规律
		 * 	条数：sumpage
			页数：pagenum
			开始数：(pagenum-1)*sumpage+1
			结束条：sumpage*pagenum
		 */
		int start=(pagenum-1)*sum+1;
		int end=pagenum*sum;
		
		return empDao.findEmp(lowName,start, end);
	}

	@Override
	public List<Emp> findEmp1(CursorResult cursorResult) {
		return empDao.findEmp1(cursorResult);
	}

}
