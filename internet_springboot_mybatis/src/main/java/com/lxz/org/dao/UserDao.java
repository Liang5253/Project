package com.lxz.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lxz.org.pojo.UserBean;

@Mapper
public interface UserDao {
	public int insertUser(UserBean user);
	
	public int updateUser(UserBean user);
	
	public int deleteUser(Integer id);
	
	public UserBean getUser(Integer id);
	
	public List<UserBean> findUsers(String userName);

}