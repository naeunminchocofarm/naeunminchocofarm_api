package com.naeunminchocofarm.ncf_api.member.entity;

public class LoginInfo {
	private Integer id;
	private String loginId;
	private String name;
	private String email;
	private String tell;
	private String roleName;
	private Integer roleFlag;

	public LoginInfo() {
	}

	public LoginInfo(Integer id, String loginId, String name, String email, String tell, String roleName, Integer roleFlag) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleFlag() {
		return roleFlag;
	}

	public void setRoleFlag(Integer roleFlag) {
		this.roleFlag = roleFlag;
	}
}
