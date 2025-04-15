package com.naeunminchocofarm.ncf_api.member.dto;

public class LoginResponeDTO {
	private String loginId;
	private String encryptedLoginPw;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEncryptedLoginPw() {
		return encryptedLoginPw;
	}

	public void setEncryptedLoginPw(String encryptedLoginPw) {
		this.encryptedLoginPw = encryptedLoginPw;
	}
}
