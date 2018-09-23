package lxz.org.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lxz.org.dao.EmpDao;
import lxz.org.pojo.Emp;
import lxz.org.service.EmpService;


@Service("empService")
public class EmpServiceImpl implements EmpService {
		
		@Autowired
		private EmpDao empDao;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRES_NEW)
	public List<Emp> getAll() {
		return empDao.selectAll();
	}

}
