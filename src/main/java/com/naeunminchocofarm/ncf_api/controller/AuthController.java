package com.naeunminchocofarm.ncf_api.controller;

import com.naeunminchocofarm.ncf_api.lib.exception.ApiException;
import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import com.naeunminchocofarm.ncf_api.lib.security.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.security.AuthUser;
import com.naeunminchocofarm.ncf_api.member.dto.LoginInfoDTO;
import com.naeunminchocofarm.ncf_api.member.dto.LoginRequest;
import com.naeunminchocofarm.ncf_api.member.dto.SignupRequest;
import com.naeunminchocofarm.ncf_api.member.entity.Member;
import com.naeunminchocofarm.ncf_api.member.service.MemberService;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SimpleFarmDTO;
import com.naeunminchocofarm.ncf_api.smart_farm.service.FarmService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {
    private static final Logger log = LogManager.getLogger(AuthController.class);

    private final JwtHandler jwtHandler;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final FarmService farmService;

    public AuthController(JwtHandler jwtHandler, MemberService memberService, PasswordEncoder passwordEncoder, FarmService farmService) {
        this.jwtHandler = jwtHandler;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.farmService = farmService;
    }

    @PostMapping("/member/login")
    public ResponseEntity<LoginInfoDTO> login(@RequestBody LoginRequest loginRequest) {
        var loginInfoDto = memberService.login(loginRequest);
        var accessToken = jwtHandler.generateAccessToken(createMemberAccessClaims(loginInfoDto));
        var refreshToken = jwtHandler.generateRefreshToken(createMemberRefreshClaims(loginInfoDto));

        var refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .sameSite("Strict")
                .secure(true)
                .build();

        return ResponseEntity.ok()
                .headers(httpHeaders -> {
                    httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
                    httpHeaders.set(HttpHeaders.AUTHORIZATION, accessToken);
                    httpHeaders.set(HttpHeaders.SET_COOKIE, refreshCookie.toString());
                })
                .body(loginInfoDto);
    }

    private Map<String, Serializable> createMemberRefreshClaims(LoginInfoDTO loginInfoDto) {
        var refreshClaims = new HashMap<String, Serializable>();
        refreshClaims.put("id", loginInfoDto.getId());
        return refreshClaims;
    }

    private Map<String, Serializable> createMemberAccessClaims(LoginInfoDTO loginInfoDto) {
        var accessClaims = new HashMap<String, Serializable>();
        accessClaims.put("id", loginInfoDto.getId());
        accessClaims.put("roleName", loginInfoDto.getRoleName());
        accessClaims.put("roleFlag", loginInfoDto.getRoleFlag());
        return accessClaims;
    }

    @PostMapping("/app/login")
    public ResponseEntity<LoginInfoDTO> loginApp(@RequestBody LoginRequest loginRequest) {
        LoginInfoDTO loginInfoDto = memberService.login(loginRequest);
        String accessToken = jwtHandler.generateIndefiniteAccessToken(createMemberAccessClaims(loginInfoDto));

        return ResponseEntity.ok()
                .headers(httpHeaders -> {
                    httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
                    httpHeaders.set(HttpHeaders.AUTHORIZATION, accessToken);
                })
                .body(loginInfoDto);
    }

    @PostMapping("/farm/login")
    public ResponseEntity<LoginInfoDTO> loginFarm(@RequestParam("farm-uuid") String farmUuid) {
        var simpleFarmDto = farmService.getFarmByUuid(farmUuid).orElseThrow(() -> new ApiException("farm-uuid를 다시 확인해주시기 바랍니다.", "INVALID_UUID", HttpStatus.UNAUTHORIZED));
        var accessToken = jwtHandler.generateIndefiniteAccessToken(createFarmAccessClaims(simpleFarmDto));

        return ResponseEntity.noContent()
                .headers(httpHeaders -> {
                    httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
                    httpHeaders.set(HttpHeaders.AUTHORIZATION, accessToken);
                })
                .build();
    }

    private Map<String, Serializable> createFarmAccessClaims(SimpleFarmDTO simpleFarmDto) {
        var claims = new HashMap<String, Serializable>();
        claims.put("id", simpleFarmDto.id());
        claims.put("uuid", simpleFarmDto.uuid());
        claims.put("roleName", simpleFarmDto.roleName());
        claims.put("roleFlag", simpleFarmDto.roleFlag());
        return claims;
    }

    @PostMapping("/member/refresh")
    public ResponseEntity<LoginInfoDTO> refresh(@CookieValue("refreshToken") Optional<String> refreshTokenOptional) {
        var refreshToken = refreshTokenOptional.orElseThrow(() -> new ApiException("리프레쉬 토큰이 존재하지 않습니다.", "EMPTY_REFRESH", HttpStatus.BAD_REQUEST));
        var claims = this.jwtHandler.parseRefreshToken(refreshToken);
        var memberId = claims.get("id", Integer.class);
        var loginInfoDto = memberService.loginById(memberId);
        var accessToken = jwtHandler.generateAccessToken(createMemberAccessClaims(loginInfoDto));
        return ResponseEntity.ok()
                .headers(httpHeaders -> {
                    httpHeaders.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
                    httpHeaders.set(HttpHeaders.AUTHORIZATION, accessToken);
                })
                .body(loginInfoDto);
    }

    @DeleteMapping("/member/refresh")
    public ResponseEntity<LoginInfoDTO> logout(@CookieValue("refreshToken") Optional<String> refreshTokenOptional) {
        var refreshToken = refreshTokenOptional.orElse("");
        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .secure(true)
                .build();
        return ResponseEntity.noContent()
                .headers(httpHeaders -> {
                    httpHeaders.set(HttpHeaders.SET_COOKIE, refreshCookie.toString());
                })
                .build();
    }

    @PostMapping("/member/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest request) {
        log.info("회원가입 : {}", request.getLoginId());

        Member member = Member.from(request, passwordEncoder);
        memberService.signUp(member);

        return ResponseEntity.ok("회원가입 완료");
    }
}
