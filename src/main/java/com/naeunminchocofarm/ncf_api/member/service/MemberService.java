package com.naeunminchocofarm.ncf_api.member.service;

import com.naeunminchocofarm.ncf_api.lib.exception.ApiException;
import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.dto.LoginInfoDTO;
import com.naeunminchocofarm.ncf_api.member.dto.LoginRequest;
import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.mapper.MemberMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
	private static final Logger log = LogManager.getLogger(MemberService.class);
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public List<MemberDTO> getMemberList (Pagination pagination) {
		return this.memberMapper.getMemberList(pagination).stream()
						.map( MemberDTO::from ).toList();
	};

	public void signUp(Member member) {
		System.out.println("회원가입 정보: " + member.getLoginId());
		memberMapper.signUp(member);
	};

	public LoginInfoDTO login (LoginRequest request) {
		Member member = memberMapper.findByLoginId(request.getLoginId())
				.orElseThrow(() -> new ApiException("회원정보를 찾을 수 없습니다.", "NOT_FOUND_MEMBER", HttpStatus.NOT_FOUND));

		if (!passwordEncoder.matches(request.getPassword(), member.getEncryptedLoginPw())) {
			throw new ApiException("비밀번호를 확인해주세요", "INVALID_PW", HttpStatus.UNAUTHORIZED);
		}

		return new LoginInfoDTO(member.getId(), member.getMemberRole().getRoleName());
	};

	public LoginInfo getMemInfo(Integer id) {
		return memberMapper.getMemInfo(id);
	}
}
