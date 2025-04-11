package com.naeunminchocofarm.ncf_api.member.dto;

public class MemberRoleDTO {
	private final Integer id;
	private final String memberRole;

	public MemberRoleDTO(Integer id, String memberRole) {
		this.id = id;
		this.memberRole = memberRole;
	}

	public Integer getId() {
		return id;
	}

	public String getMemberRole() {
		return memberRole;
	}
}


