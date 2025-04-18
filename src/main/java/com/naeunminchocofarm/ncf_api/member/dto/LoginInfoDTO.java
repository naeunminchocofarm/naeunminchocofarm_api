package com.naeunminchocofarm.ncf_api.member.dto;

public class LoginInfoDTO {
	private final Integer id;
	private final String loginId;
	private final String name;
	private final String email;
	private final String tell;
	private final String roleName;
	private final Integer roleFlag;

	public LoginInfoDTO(Integer id, String loginId, String name, String email, String tell, String roleName, Integer roleFlag) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.email = email;
		this.tell = tell;
		this.roleName = roleName;
		this.roleFlag = roleFlag;
	}

	public Integer getId() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getName() {
		return name;
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
