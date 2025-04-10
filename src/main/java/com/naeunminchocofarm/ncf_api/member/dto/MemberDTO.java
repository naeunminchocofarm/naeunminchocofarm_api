package com.naeunminchocofarm.ncf_api.member.dto;

import com.naeunminchocofarm.ncf_api.member.entity.Member;

import java.time.OffsetDateTime;

public class MemberDTO {
	private final Integer id;
	private final String loginId;
	private final String encryptedLoginPw;
	private final String name;
	private final String email;
	private final String tell;
	private final boolean privacyPolicy;
	private final OffsetDateTime createdAt;
	private final OffsetDateTime deletedAt;
	private final String memo;

	public MemberDTO(Integer id, String loginId, String encryptedLoginPw, String name, String email, String tell, boolean privacyPolicy, OffsetDateTime createdAt, OffsetDateTime deletedAt, String memo) {
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

	public boolean isPrivacyPolicy() {
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

	public static MemberDTO from(Member member){
		return new MemberDTO(member.getId(), member.getLoginId(), member.getEncryptedLoginPw(),member.getName(),member.getEmail(),member.getTell(), member.getPrivacyPolicy() , member.getCreatedAt(), member.getDeletedAt(), member.getMemo());
		//아 순서대로여야하는구나 다~데려가야함
	}


}
