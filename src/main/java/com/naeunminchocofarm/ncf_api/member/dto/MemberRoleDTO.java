package com.naeunminchocofarm.ncf_api.member.dto;

public class MemberRoleDTO {
	private Integer id;
	private String memberRole;

	public MemberRoleDTO() {
	}

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


