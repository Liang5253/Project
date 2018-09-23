package com.lxz.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lxz.org.pojo.CursorResult;
import com.lxz.org.pojo.Emp;


@Mapper
public interface EmpDao {
	
	/**
	 * 指定员工编号查询
	 * @return
	 */
	public Emp getEmp(Integer id);
	/**
	 * 查询全部员工
	 * @return 员工集合
	 */
	public List<Emp> getAll();
	
	
	/**
	 * @param so  排序条件,指定列来升序排序
	 * @param start 指定排序后从第几条开始查
	 * @param end 指定排序后到第几条结束
	 * @return
	 */
	public List<Emp> findEmp(@Param("so")String so,@Param("start")Integer start ,@Param("end") Integer end);
	
	/**
	 * 使用过程来分页查询
	 * @param cursorResult
	 * @return
	 */
	public List<Emp> findEmp1(CursorResult cursorResult);
	
	

}
