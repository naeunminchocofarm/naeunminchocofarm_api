package com.naeunminchocofarm.ncf_api.controller;

import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import com.naeunminchocofarm.ncf_api.member.dto.LoginInfoDTO;
import com.naeunminchocofarm.ncf_api.member.dto.LoginRespone;
import com.naeunminchocofarm.ncf_api.member.dto.SignupRequest;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @PostMapping("/web/login")
    public ResponseEntity<?> login(@RequestBody LoginRespone loginRespone, HttpServletResponse response) throws IOException {
        log.info("로그인ID: {}", loginRespone.getLoginId());
        log.info("로그인PW: {}", loginRespone.getEncryptedLoginPw());
        Member loginMember = Member.from(loginRespone, passwordEncoder);
        Member authenticatedMember = memberService.login(loginMember);

        if (authenticatedMember != null && authenticatedMember.getMemberRole() != null) {
            String roleName = authenticatedMember.getMemberRole().getRoleName();
            if (!roleName.startsWith("ROLE_")) {
                roleName = "ROLE_" + roleName;
            }
            Integer roleFlag = authenticatedMember.getMemberRole().getId();

            String token = jwtHandler.generateToken(
                    authenticatedMember.getId(), roleName, roleFlag
            );

            response.setHeader("Authorization", "Bearer " + token);
            log.info("발급 토큰: {}", token);
            log.info("로그인5: {}", roleName);
            log.info("로그인6: {}", roleFlag);
            log.info("권한 이름: {}", authenticatedMember.getMemberRole().getRoleName());
            log.info("권한 플래그: {}", authenticatedMember.getMemberRole().getId());

            //마이페이지를 위해 미리
            LoginInfoDTO loginInfo = new LoginInfoDTO(
                    token,
                    authenticatedMember.getName(),
                    authenticatedMember.getLoginId(),
                    authenticatedMember.getEmail(),
                    authenticatedMember.getTell(),
                    roleName,
                    roleFlag
            );

            return ResponseEntity.ok(loginInfo);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @PostMapping("/web/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest request) {
        log.info("회원가입 : {}", request.getLoginId());

        Member member = Member.from(request, passwordEncoder);
        memberService.signUp(member);

        return ResponseEntity.ok("회원가입 완료");
    }
}
