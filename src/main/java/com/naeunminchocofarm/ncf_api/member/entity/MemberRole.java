package com.naeunminchocofarm.ncf_api.member.entity;

public class MemberRole {
	private Integer id;
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public MemberRole() {
	}

	public MemberRole(Integer id, String role) {
		this.id = id;
		this.role = role;
	}
}
