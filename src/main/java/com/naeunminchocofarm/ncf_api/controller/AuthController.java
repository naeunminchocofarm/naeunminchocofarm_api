package com.naeunminchocofarm.ncf_api.controller;

import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import com.naeunminchocofarm.ncf_api.member.dto.LoginRequestDTO;
import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
import com.naeunminchocofarm.ncf_api.member.dto.SignupRequestDTO;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.entity.MemberRole;
import com.naeunminchocofarm.ncf_api.member.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class AuthController {

    private static final Logger log = LogManager.getLogger(AuthController.class);

    private final JwtHandler jwtHandler;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtHandler jwtHandler, MemberService memberService, PasswordEncoder passwordEncoder) {
        this.jwtHandler = jwtHandler;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostMapping("/web/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request, HttpServletResponse response) {
//        log.info("로그인: {}", request.getLoginId());
//
//        MemberDTO authenticatedMember = memberService.login(request.getLoginId(), request.getEncryptedLoginPw());
//
//        if (authenticatedMember != null) {
//            String token = jwtHandler.generateToken(
//                    authenticatedMember.getId(),
//                    authenticatedMember.getRoleNames(),
//                    authenticatedMember.getRoleFlags()
//            );
//            response.setHeader("Authorization", "Bearer " + token);
//            return ResponseEntity.ok("Bearer " + token);
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//
//    }

    @PostMapping("/web/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequestDTO request) {
        log.info("회원가입 요청: {}", request.getLoginId());

        Member member = Member.from(request, passwordEncoder);
        memberService.signUp(member);

        return ResponseEntity.ok("회원가입 완료");
    }
}
