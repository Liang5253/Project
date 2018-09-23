package com.lxz.org.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RoleBean implements Serializable {
	private Integer roleId;
	private String roleName;
	@JsonFormat(pattern="yy-MM-dd : HH:mm:ss",timezone="GMT+8",locale="zh")
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
