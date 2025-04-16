package com.naeunminchocofarm.ncf_api.member.entity;

public class MemberRole {
	private Integer roleFlag;
	private String roleName;

	public MemberRole(Integer roleFlag, String roleName) {
		this.roleFlag = roleFlag;
		this.roleName = roleName;
	}

	public Integer getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(Integer roleFlag) {
		this.roleFlag = roleFlag;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
