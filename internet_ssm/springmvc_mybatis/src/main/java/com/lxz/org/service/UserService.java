package com.lxz.org.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lxz.org.pojo.UserBean;

public interface UserService {
	public int insertUser(UserBean user);
	
	public int updateUser(UserBean user);
	
	public int deleteUser(Integer id);
	
	public UserBean getUser(Integer id);
	
	public List<UserBean> findUsers(String userName, int start ,int limit);
}
