package com.jk.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class UsersInfo extends Page implements Serializable{

	private Integer userId;
	
	private String fileUrl;
	
	private String userName;
	
	private String userPassword;

	private String loginInfo;
	
	private String ids;
	
	private String userPhoto;
	
	private String codeImg;
	
	private String qrCode;
	
	private String selfQrCode;
	
	private String selfImg;
	
	private Integer loginFailNum;
	
	private Long loginFailDate;
	
	private Integer roleID;
	
	
	
	
	
	
	
	
	
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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(String loginInfo) {
		this.loginInfo = loginInfo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getCodeImg() {
		return codeImg;
	}

	public void setCodeImg(String codeImg) {
		this.codeImg = codeImg;
	}

	public String getSelfImg() {
		return selfImg;
	}

	public void setSelfImg(String selfImg) {
		this.selfImg = selfImg;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getSelfQrCode() {
		return selfQrCode;
	}

	public void setSelfQrCode(String selfQrCode) {
		this.selfQrCode = selfQrCode;
	}

	public Integer getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(Integer loginFailNum) {
		this.loginFailNum = loginFailNum;
	}

	public Long getLoginFailDate() {
		return loginFailDate;
	}

	public void setLoginFailDate(Long loginFailDate) {
		this.loginFailDate = loginFailDate;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Override
	public String toString() {
		return "UsersInfo [userId=" + userId + ", fileUrl=" + fileUrl + ", userName=" + userName + ", userPassword="
				+ userPassword + ", loginInfo=" + loginInfo + ", ids=" + ids + ", userPhoto=" + userPhoto + ", codeImg="
				+ codeImg + ", qrCode=" + qrCode + ", selfQrCode=" + selfQrCode + ", selfImg=" + selfImg
				+ ", loginFailNum=" + loginFailNum + ", loginFailDate=" + loginFailDate + ", roleID=" + roleID + "]";
	}
	
	
}
