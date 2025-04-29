package com.naeunminchocofarm.ncf_api.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.naeunminchocofarm.ncf_api.member.dto.LoginRequest;
import com.naeunminchocofarm.ncf_api.member.dto.SignupRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;


public class Member {
	private Integer id;
	private String loginId;
	private String encryptedLoginPw;
	private String name;
	private String email;
	private String tell;
	private boolean privacyPolicy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private OffsetDateTime createdAt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private OffsetDateTime deletedAt;
	private String memo;
	private MemberRole memberRole;

	public Member(Integer id, String loginId, String encryptedLoginPw, String name, String email, String tell, boolean privacyPolicy, OffsetDateTime createdAt, OffsetDateTime deletedAt, String memo, MemberRole memberRole) {
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
		this.memberRole = memberRole;
	}

	public Member() {
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

	public boolean getPrivacyPolicy() {
		return privacyPolicy;
	}

	public void setPrivacyPolicy(boolean privacyPolicy) {
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

	public boolean isPrivacyPolicy() {
		return privacyPolicy;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public static Member from(SignupRequest request, PasswordEncoder passwordEncoder) {
		Member member = new Member();// 각각에 넣어주는
		member.setLoginId(request.getLoginId());
		member.setEncryptedLoginPw(passwordEncoder.encode(request.getEncryptedLoginPw()));
		member.setName(request.getName());
		member.setEmail(request.getEmail());
		member.setTell(request.getTell());
		member.setPrivacyPolicy(request.isPrivacyPolicy());
		member.setMemberRole(new MemberRole(1, "USER"));
		return member;
	}

	public static Member from(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
		Member member = new Member();// 로그인시 비교를 위해 넣어주는
		member.setLoginId(loginRequest.loginId());
		member.setEncryptedLoginPw(loginRequest.password());
		return member;
	}

}
