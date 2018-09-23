package com.lxz.org.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.lxz.org.dao.RoleDao;
import com.lxz.org.pojo.RoleBean;
import com.lxz.org.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertRole(RoleBean role) {
		return this.roleDao.insertRole(role);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int updateRole(RoleBean role) {
		return  this.roleDao.updateRole(role);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteRole(Integer id) {
		return this.roleDao.deleteRole(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public RoleBean getRole(Integer id) {
		return this.roleDao.getRole(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<RoleBean> findRoles(String roleName) {
		return this.roleDao.findRoles(roleName);
	}


}
