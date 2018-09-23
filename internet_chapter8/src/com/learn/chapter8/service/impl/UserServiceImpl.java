package com.learn.chapter8.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.learn.chapter8.dao.UserDao;
import com.learn.chapter8.pojo.UserBean;
import com.learn.chapter8.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertUser(UserBean user) {
		return this.userDao.insertUser(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int updateUser(UserBean user) {
		return  this.userDao.updateUser(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int deleteUser(Integer id) {
		return this.userDao.deleteUser(id);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.READ_COMMITTED)
	public UserBean getUser(Integer id) {
		return this.userDao.getUser(id);
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.SUPPORTS)
	public List<UserBean> findUsers(String userName, int start, int limit) {
		return this.userDao.findUsers(userName, new RowBounds(start,limit));
	}

}
