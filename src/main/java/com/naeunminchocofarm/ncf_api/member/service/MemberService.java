package com.naeunminchocofarm.ncf_api.member.service;

import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.mapper.MemberMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
	private static final Logger log = LogManager.getLogger(MemberService.class);
	private final MemberMapper memberMapper;

	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	public List<MemberDTO> getMemberList (Pagination pagination) {
		return this.memberMapper.getMemberList(pagination).stream()
						.map( MemberDTO::from ).toList();
	};
	public void signUp(Member member) {

		System.out.println("회원가입 정보: " + member.getLoginId());
		memberMapper.signUp(member);
	};
	public void login (Member member) {
		this.memberMapper.login(member);
	};
}
