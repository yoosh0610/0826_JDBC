package com.kh.statement.model.dto;

import java.util.Objects;

/*
 * DTO(Date Transfer OBject)
 * 
 * 데이터를 전송하는데 목적을 둔 객체
 * 와따리가따리할 때 씀
 * 
 * 사실 VO클래스는 불변성을 가져야하기 때문에 setter메소드를 가지고 있을수 없음
 * 데이터 전송이 목적일 땐 VO대신 DTO클래스를 만들어서 계층간 이동하는 용도로 사용함
 */
public class PasswordDTO {
	
	 private String userId;
	 private String userPwd;
	 private String newPassword;
	public PasswordDTO() {
		super();
	}
	public PasswordDTO(String userId, String userPwd, String newPassword) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.newPassword = newPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "PasswordDTO [userId=" + userId + ", userPwd=" + userPwd + ", newPassword=" + newPassword + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(newPassword, userId, userPwd);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordDTO other = (PasswordDTO) obj;
		return Objects.equals(newPassword, other.newPassword) && Objects.equals(userId, other.userId)
				&& Objects.equals(userPwd, other.userPwd);
	}
	 
	 
	 
	
}
