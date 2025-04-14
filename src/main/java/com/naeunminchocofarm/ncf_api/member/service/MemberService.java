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
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	private final MemberMapper memberMapper;


	public Member signUp(Member member) {
		return memberMapper.signUp(member);
	};
	public Member login (Member member) {
		return memberMapper.login(member);
	}
	public List<MemberDTO> getMemberList (Pagination pagination) {
		log.info("hello, page = {}, size = {}", pagination.getPage(), pagination.getSize());
		return this.memberMapper.getMemberList(pagination).stream()
						.map( MemberDTO::from ).toList();
	}
}
