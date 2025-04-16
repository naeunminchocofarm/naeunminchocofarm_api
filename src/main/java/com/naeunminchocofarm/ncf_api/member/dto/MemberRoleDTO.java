package com.naeunminchocofarm.ncf_api.member.dto;

public class MemberRoleDTO {
	private final Integer roleFlage;
	private final String roleName;

	public MemberRoleDTO(Integer roleFlage, String roleName) {
		this.roleFlage = roleFlage;
		this.roleName = roleName;
	}

	public Integer getRoleFlage() {
		return roleFlage;
	}

	public String getRoleName() {
		return roleName;
	}
}


