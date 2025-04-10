package com.naeunminchocofarm.ncf_api.member.dto;

import java.time.OffsetDateTime;

public class MemberDTO {
	private Integer id;
	private String loginId;
	private String encryptedLoginPw;
	private String name;
	private String email;
	private String tell;
	private Boolean privacyPolicy;
	private OffsetDateTime createdAt;
	private OffsetDateTime deletedAt;
	private String memo;

	public MemberDTO() {
	}

	public MemberDTO(Integer id, String loginId, String encryptedLoginPw, String name, String email, String tell, Boolean privacyPolicy, OffsetDateTime createdAt, OffsetDateTime deletedAt, String memo) {
		this.id = id;
		this.loginId = loginId;
		this.encryptedLoginPw = encryptedLoginPw;
		this.name = name;
		this.email = email;
		this.tell = tell;
		this.privacyPolicy = privacyPolicy;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
		this.memo = memo;
	}

	public Integer getId() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getEncryptedLoginPw() {
		return encryptedLoginPw;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getTell() {
		return tell;
	}

	public Boolean getPrivacyPolicy() {
		return privacyPolicy;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public OffsetDateTime getDeletedAt() {
		return deletedAt;
	}

	public String getMemo() {
		return memo;
	}
}
