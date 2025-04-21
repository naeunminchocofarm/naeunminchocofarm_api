package com.naeunminchocofarm.ncf_api.member.dto;

public class LoginRequest {
	private String loginId;
	private String password;

	public LoginRequest(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public LoginRequest() {
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
