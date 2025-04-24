package com.naeunminchocofarm.ncf_api.member.controller;

import com.naeunminchocofarm.ncf_api.lib.exception.ApiException;
import com.naeunminchocofarm.ncf_api.lib.pagination.Pagination;
import com.naeunminchocofarm.ncf_api.lib.security.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.security.AuthUser;
import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.dto.MemberInfoDTO;
import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.service.MemberService;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.FarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.dto.SimpleFarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.service.FarmService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	private final FarmService farmService;

	public MemberController(MemberService memberService, PasswordEncoder passwordEncoder, FarmService farmService) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
		this.farmService = farmService;
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

	@GetMapping("/member/farms")
	public List<SimpleFarmDTO> getMemberFarms(@AuthInfo() AuthUser authUser) {
		return farmService.getFarmsByMemberId(authUser.getId());
	}

	@GetMapping("/member/farms/{id}")
	public Optional<SimpleFarmDTO> getMemberFarms(@AuthInfo() AuthUser authUser, @PathVariable("id") Integer farmId) {
		return farmService.getFarmByIdAndMemberId(farmId, authUser.getId());
	}

	@GetMapping("/member/memberInfo")
	public Optional<MemberInfoDTO> getMemberInfo(@AuthInfo() AuthUser authUser) {
		return memberService.getMemberInfo(authUser.getId());
	}
}