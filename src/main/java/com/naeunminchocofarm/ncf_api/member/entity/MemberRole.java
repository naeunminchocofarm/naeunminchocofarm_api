package com.naeunminchocofarm.ncf_api.member.entity;

public class MemberRole {
	private Integer id;
	private String memberRole;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}

	public MemberRole() {

	}

	public MemberRole(Integer id, String memberRole) {
		this.id = id;
		this.memberRole = memberRole;
	}
}
