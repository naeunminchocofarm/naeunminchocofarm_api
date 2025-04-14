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

	public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;


	//회원가입
	@PostMapping("/user/signup")
	public ResponseEntity<?> signUp(Member member){
		System.out.println("회원가입 작동중");

		String encryptedLoginPw = passwordEncoder.encode(member.getEncryptedLoginPw());
		member.setEncryptedLoginPw(encryptedLoginPw);
		memberService.signUp(member);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/admin/members")
	public List<MemberDTO> getMemberList(@RequestParam(value = "interval", defaultValue = "") String rawInterval
					, @RequestParam(value = "size", defaultValue = "15") Integer page
					, @RequestParam(value = "page", defaultValue = "1") Integer size){
		Pagination pagination;
		try {
			pagination = new Pagination(size,page);
		}catch (IllegalArgumentException ex){
			throw new ApiException(ex.getMessage(), "BAD_REQUEST", HttpStatus.BAD_REQUEST);
		}

		return memberService.getMemberList(pagination);
	};
}
