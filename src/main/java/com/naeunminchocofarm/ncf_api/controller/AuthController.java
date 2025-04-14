package com.naeunminchocofarm.ncf_api.controller;

import com.naeunminchocofarm.ncf_api.lib.auth.ExpiredAuthorizationDataException;
import com.naeunminchocofarm.ncf_api.lib.auth.RequestAuth;
import com.naeunminchocofarm.ncf_api.lib.auth.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import com.naeunminchocofarm.ncf_api.member.dto.MemberDTO;
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

import java.util.Set;

@RestController
public class AuthController {

    private static final Logger log = LogManager.getLogger(AuthController.class);
    private final JwtHandler jwtHandler;
    private final MemberService memberService;

    public AuthController(JwtHandler jwtHandler, MemberService memberService) {
        this.jwtHandler = jwtHandler;
        this.memberService = memberService;
    }



//    @PostMapping("/web/login")
//    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO, HttpServletResponse response) throws Exception {
//        System.out.println("로그인 처리중");
//
//        // 로그인 처리 후 MemberDTO 객체 반환
//        MemberDTO authenticatedMember = memberService.login(memberDTO);
//
//        if (authenticatedMember != null) {
//            // 인증 성공 시, JWT 토큰 발급
//            String token = jwtHandler.generateToken(authenticatedMember.getId(), authenticatedMember.getRoleNames(), authenticatedMember.getRoleFlags());
//            response.setHeader("Authorization", "Bearer " + token); // 토큰을 HTTP 헤더에 설정
//            return ResponseEntity.ok().body("Bearer " + token); // 클라이언트에 토큰을 반환
//        } else {
//            // 인증 실패 시, 401 Unauthorized 응답
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
}
