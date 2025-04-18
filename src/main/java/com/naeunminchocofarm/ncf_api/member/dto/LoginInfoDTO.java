package com.naeunminchocofarm.ncf_api.member.dto;

public class LoginInfoDTO {
	private final Integer id;
	private final String roleName;

	public LoginInfoDTO(Integer id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}
	public String getRoleName() {
		return roleName;
	}
}
