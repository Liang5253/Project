package com.learn.chapter8.pojo;

import java.util.Date;

public class RoleBean {
	private Integer roleId;
	private String roleName;
	private Date roleCreateDate;
	private String roleNote;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getRoleCreateDate() {
		return roleCreateDate;
	}
	public void setRoleCreateDate(Date roleCreateDate) {
		this.roleCreateDate = roleCreateDate;
	}
	public String getRoleNote() {
		return roleNote;
	}
	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}
	@Override
	public String toString() {
		return "RoleBean [roleId=" + roleId + ", roleName=" + roleName + ", roleCreateDate=" + roleCreateDate
				+ ", roleNote=" + roleNote + "]";
	}
	
	
}
