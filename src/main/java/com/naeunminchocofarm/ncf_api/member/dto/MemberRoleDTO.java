package com.naeunminchocofarm.ncf_api.member.dto;

public class MemberRoleDTO {
	private final Integer id;
	private final String role;

	public MemberRoleDTO(Integer id, String role) {
		this.id = id;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public String getRole() {
		return role;
	}
}


