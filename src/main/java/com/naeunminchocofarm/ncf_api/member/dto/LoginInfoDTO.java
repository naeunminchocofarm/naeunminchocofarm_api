package com.naeunminchocofarm.ncf_api.member.dto;

public class LoginInfoDTO {
	private String token;
	private String id;
	private String loginId;
	private String name;
	private String email;
	private String tell;
	private String roleName;
	private Integer roleFlag;

	public LoginInfoDTO(String token, String name, String loginId, String email, String tell, String roleName, Integer roleFlag) {
		this.token = token;
		this.name = name;
		this.loginId = loginId;
		this.email = email;
		this.tell = tell;
		this.roleName = roleName;
		this.roleFlag = roleFlag;
	}

	public String getToken() {
		return token;
	}

	public String getName() {
		return name;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getEmail() {
		return email;
	}

	public String getTell() {
		return tell;
	}

	public String getRoleName() {
		return roleName;
	}

	public Integer getRoleFlag() {
		return roleFlag;
	}
}
