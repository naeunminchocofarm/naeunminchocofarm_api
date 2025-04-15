package com.naeunminchocofarm.ncf_api.member.service;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.mapper.MemberMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	public Member login (Member member) {
		Member checkMember = memberMapper.login(member);

		if (!passwordEncoder.matches(member.getEncryptedLoginPw(), checkMember.getEncryptedLoginPw())) {
			throw new RuntimeException("아이디 혹은 비밀번호를 다시 확인해 주세요.");
		}

		if (checkMember == null){
			throw new RuntimeException("아이디 혹은 비밀번호를 다시 확인해 주세요.");
		}
		log.info("로그인 성공: {}", member.getLoginId());
		return checkMember;
	};
}
