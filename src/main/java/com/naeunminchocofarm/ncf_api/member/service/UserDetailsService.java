package com.naeunminchocofarm.ncf_api.member.service;

import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private MemberService memberService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = new Member();

		if (member == null) throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");

		return new User(
						member.getLoginId(),
						member.getEncryptedLoginPw(),
						List.of(new SimpleGrantedAuthority(member.getMemberRole().getMemberRole())) // 예: ROLE_USER
		);	}
}
