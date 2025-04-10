package com.naeunminchocofarm.ncf_api.member.entity;

import java.time.OffsetDateTime;

public class Member {
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

	public Member() {
	}

	public Member(Integer id, String loginId, String encryptedLoginPw, String name, String email, String tell, Boolean privacyPolicy, OffsetDateTime createdAt, OffsetDateTime deletedAt, String memo) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEncryptedLoginPw() {
		return encryptedLoginPw;
	}

	public void setEncryptedLoginPw(String encryptedLoginPw) {
		this.encryptedLoginPw = encryptedLoginPw;
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

	public Boolean getPrivacyPolicy() {
		return privacyPolicy;
	}

	public void setPrivacyPolicy(Boolean privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public OffsetDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(OffsetDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
