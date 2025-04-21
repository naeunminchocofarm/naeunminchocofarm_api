package com.naeunminchocofarm.ncf_api.member.dto;

import com.naeunminchocofarm.ncf_api.member.entity.Member;

public class LoginInfoDTO {
	private final Integer id;
	private final String roleName;
	private final Integer roleFlag;
	private final String loginId;
	private final String email;
	private final String name;
	private final String tell;

	public LoginInfoDTO(Integer id, String roleName, Integer roleFlag, String loginId, String email, String name, String tell) {
		this.id = id;
		this.roleName = roleName;
		this.roleFlag = roleFlag;
		this.loginId = loginId;
		this.email = email;
		this.name = name;
		this.tell = tell;
	}

	public static LoginInfoDTO from(Member member) {
		return new LoginInfoDTO(
				member.getId(),
				member.getMemberRole().getRoleName(),
				member.getMemberRole().getRoleFlag(),
				member.getLoginId(),
				member.getEmail(),
				member.getName(),
				member.getTell()
		);
    }

    public Integer getId() {
		return id;
	}
	public String getRoleName() {
		return roleName;
	}

	public Integer getRoleFlag() {
		return roleFlag;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getTell() {
		return tell;
	}
}
