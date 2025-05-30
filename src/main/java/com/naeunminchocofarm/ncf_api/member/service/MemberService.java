package com.naeunminchocofarm.ncf_api.member.service;

import com.naeunminchocofarm.ncf_api.lib.exception.ApiException;
import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.dto.LoginInfoDTO;
import com.naeunminchocofarm.ncf_api.member.dto.LoginRequest;
import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.dto.MemberInfoDTO;
import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.entity.MemberImg;
import com.naeunminchocofarm.ncf_api.member.mapper.MemberMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
		Member member = memberMapper.findByLoginId(request.loginId())
				.orElseThrow(() -> new ApiException("회원정보를 찾을 수 없습니다.", "NOT_FOUND_MEMBER", HttpStatus.NOT_FOUND));

		if (!passwordEncoder.matches(request.password(), member.getEncryptedLoginPw())) {
			throw new ApiException("비밀번호를 확인해주세요", "INVALID_PW", HttpStatus.UNAUTHORIZED);
		}

		return LoginInfoDTO.from(member);
	};

	public LoginInfoDTO loginById(Integer id) {
		var member = memberMapper.findById(id)
				.orElseThrow(() -> new ApiException("회원정보를 찾을 수 없습니다.", "NOT_FOUND_MEMBER", HttpStatus.NOT_FOUND));

		return LoginInfoDTO.from(member);
	}

    public Optional<MemberInfoDTO> getMemberInfo(Integer id) {
		return memberMapper.getMemberInfo(id);
    }

		// 프로필 이미지 등록
		public void insertMemberImg(MemberImg memberImg) {
			memberMapper.insertMemberImg(memberImg);
		}

		// 회원 ID로 프로필 이미지 조회
		public MemberImg getMemberImgByMemberId(Integer memberId) {
			return memberMapper.getMemberImgByMemberId(memberId);
		}

		// 프로필 이미지 수정
		public void updateMemberImg(MemberImg memberImg) {
			memberMapper.updateMemberImg(memberImg);
		}

		//프로필 이미지 삭제
		public void deleteMemberImgByMemberId(Integer memberId) {
			memberMapper.deleteMemberImgByMemberId(memberId);
		}



}
