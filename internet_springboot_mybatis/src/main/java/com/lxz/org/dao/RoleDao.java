package com.lxz.org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.lxz.org.pojo.RoleBean;


@Mapper
public interface RoleDao {
	public int insertRole(RoleBean role);
	
	public int updateRole(RoleBean role);
	
	public int deleteRole(Integer id);
	
	public RoleBean getRole(Integer id);
	
	public List<RoleBean> findRoles(String roleName);
}
