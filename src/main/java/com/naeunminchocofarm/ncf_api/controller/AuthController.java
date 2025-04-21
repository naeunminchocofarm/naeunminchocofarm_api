package com.naeunminchocofarm.ncf_api.controller;

import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import com.naeunminchocofarm.ncf_api.lib.security.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.security.AuthUser;
import com.naeunminchocofarm.ncf_api.member.dto.LoginInfoDTO;
import com.naeunminchocofarm.ncf_api.member.dto.LoginRequest;
import com.naeunminchocofarm.ncf_api.member.dto.SignupRequest;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
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

    @PostMapping("/member/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        LoginInfoDTO loginInfoDTO = memberService.login(loginRequest);
        String jwt = jwtHandler.generateToken(loginInfoDTO.getId(), loginInfoDTO.getRoleName(), null);
        return ResponseEntity.noContent().headers(httpHeaders -> {
            httpHeaders.set("Access-Control-Expose-Headers", "Authorization");
            httpHeaders.set("Authorization", "Bearer " + jwt);
        }).build();
    }

    @GetMapping("/user/test-auth-request")
    public void testAuthRequest(@AuthInfo() AuthUser authUser) {
        log.info("보안 요청 성공!!!!");
        log.info(authUser);
    }

    @GetMapping("/admin/test-auth-request")
    public void testAdminAuthRequest() {
        log.info("여길 어디라고 들어와");
    }

    @GetMapping("/test/test-auth-request")
    public void testAdminAuthRequest(@AuthInfo() AuthUser authUser) {
        log.info("여길 어디라고 들어와!!!");
    }

    @PostMapping("/member/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest request) {
        log.info("회원가입 : {}", request.getLoginId());

        Member member = Member.from(request, passwordEncoder);
        memberService.signUp(member);

        return ResponseEntity.ok("회원가입 완료");
    }
}
