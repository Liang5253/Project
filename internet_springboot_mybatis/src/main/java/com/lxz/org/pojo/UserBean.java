package com.lxz.org.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;


@JsonIgnoreType
public class UserBean implements Serializable {
	private Integer userId;
	private String userName;
	private Date userBrithday;
	private String userSex;
	private String userMobile;
	private String userEmail;
	private String userNote;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getUserBrithday() {
		return userBrithday;
	}
	public void setUserBrithday(Date userBrithday) {
		this.userBrithday = userBrithday;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserNote() {
		return userNote;
	}
	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}
	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userName=" + userName + ", userBrithday=" + userBrithday + ", userSex="
				+ userSex + ", userMobile=" + userMobile + ", userEmail=" + userEmail + ", userNote=" + userNote + "]";
	}
	
	
	
}
