package com.learn.chapter8.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import com.learn.chapter8.pojo.UserBean;

@Repository
public interface UserDao {
	public int insertUser(UserBean user);
	
	public int updateUser(UserBean user);
	
	public int deleteUser(Integer id);
	
	public UserBean getUser(Integer id);
	
	public List<UserBean> findUsers(String userName,RowBounds rowBounds);

}