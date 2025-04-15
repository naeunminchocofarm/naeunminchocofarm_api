package com.naeunminchocofarm.ncf_api.member.dto;

public class MemberRoleDTO {
	private final Integer id;
	private final String roleName;

	public Integer getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public MemberRoleDTO(Integer id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
}


