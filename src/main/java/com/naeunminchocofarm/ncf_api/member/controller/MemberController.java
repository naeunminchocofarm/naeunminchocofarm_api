package com.naeunminchocofarm.ncf_api.member.controller;

import com.naeunminchocofarm.ncf_api.lib.exception.ApiException;
import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	// 회원 목록 조회
	@GetMapping("/admin/members")
	public List<MemberDTO> getMemberList(
					@RequestParam(value = "page", defaultValue = "1") Integer page,
					@RequestParam(value = "size", defaultValue = "10") Integer size
	) {
		Pagination pagination;
		try {
			pagination = new Pagination(size, page);
		} catch (IllegalArgumentException ex) {
			throw new ApiException(ex.getMessage(), "BAD_REQUEST", HttpStatus.BAD_REQUEST);
		}

		return memberService.getMemberList(pagination);
	}
}