package com.lxz.org.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.lxz.org.dao.UserDao;
import com.lxz.org.pojo.UserBean;
import com.lxz.org.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertUser(UserBean user) {
		return this.userDao.insertUser(user);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int updateUser(UserBean user) {
		return  this.userDao.updateUser(user);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteUser(Integer id) {
		return this.userDao.deleteUser(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.READ_COMMITTED)
	public UserBean getUser(Integer id) {
		return this.userDao.getUser(id);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.SUPPORTS)
	public List<UserBean> findUsers(String userName) {
		return this.userDao.findUsers(userName);
	}

}
