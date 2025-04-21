package com.naeunminchocofarm.ncf_api.lib.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@Component
public class JwtHandler {
    private final int EXPIRATION_SECONDS;

    // RSA 비대칭 키 사용
    private final PublicKey PUBLIC_KEY;
    private final PrivateKey PRIVATE_KEY;

    public JwtHandler(@Value("${jwt.private-key}") String privateKey, @Value("${jwt.public-key}") String publicKey, @Value("${jwt.expirationSeconds}") int expirationSeconds) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final var KEY_FACTORY = KeyFactory.getInstance("RSA");
        this.PRIVATE_KEY = KEY_FACTORY.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        this.PUBLIC_KEY = KEY_FACTORY.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
        this.EXPIRATION_SECONDS = expirationSeconds;
    }

    /**
     * 액세스 토큰을 생성합니다.
     * @param id 사용자를 식별할 수 있는 아이디
     * @param roleName 사용자의 역할 이름
     * @param roleFlag 사용자의 역할 번호
     * @return 액세스 토큰
     */
    public String generateAccessToken(Integer id, String roleName, Integer roleFlag) {
        return Jwts.builder()
                .claims()
                .add("id", id)
                .add("roleName", roleName)
                .add("roleFlag", roleFlag)
                .and()
                .expiration(new Date(System.currentTimeMillis() + 1000L * this.EXPIRATION_SECONDS))
                .signWith(this.PRIVATE_KEY)
                .compact();
    }

    /**
     * 리프레쉬 토큰을 생성합니다.
     * @param id 사용자를 식별할 수 있는 식별자
     * @param roleName 역할 이름
     * @param roleFlag 역할 번호
     * @return 리프레쉬 토큰
     */
    public String generateRefreshToken(Integer id) {
        return Jwts.builder()
                .claims()
                .add("id", id)
                .and()
                .expiration(new Date(System.currentTimeMillis() + 1000L * this.EXPIRATION_SECONDS * 10))
                .signWith(this.PRIVATE_KEY)
                .compact();
    }

    /**
     * 토큰을 파싱합니다.
     * @param jwt jwt 토큰
     * @return Claims
     */
    public Claims parseToken(String jwt) {
        return Jwts.parser()
                .verifyWith(this.PUBLIC_KEY)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }


}