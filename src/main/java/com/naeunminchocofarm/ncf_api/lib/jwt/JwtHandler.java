package com.naeunminchocofarm.ncf_api.lib.jwt;

import com.naeunminchocofarm.ncf_api.lib.exception.ExpiredAuthorizationDataException;
import com.naeunminchocofarm.ncf_api.lib.exception.InvalidAuthorizationDataException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@Component
public class JwtHandler {
    private static final String TOKEN_TYPE_KEY = "token-type";
    private static final String TOKEN_TYPE_VALUE_ACCESS = "access";
    private static final String TOKEN_TYPE_VALUE_REFRESH = "refresh";
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
        var claims = new HashMap<String, Serializable>();
        claims.put("id", id);
        claims.put("roleName", roleName);
        claims.put("roleFlag", roleFlag);
        var expiration = new Date(System.currentTimeMillis() + 1000L * this.EXPIRATION_SECONDS);
        return buildAccessToken(claims, expiration);
    }

    private String buildAccessToken(Map<String, Serializable> claims, Date expiration) {
        claims.put(TOKEN_TYPE_KEY, TOKEN_TYPE_VALUE_ACCESS);
        return buildToken(claims, expiration);
    }

    private String buildRefreshToken(HashMap<String, Serializable> claims, Date expiration) {
        claims.put(TOKEN_TYPE_KEY, TOKEN_TYPE_VALUE_REFRESH);
        return buildToken(claims, expiration);
    }

    private String buildToken(Map<String, Serializable> claims, Date expiration) {
        return Jwts.builder()
                .claims(claims)
                .expiration(expiration)
                .signWith(this.PRIVATE_KEY)
                .compact();
    }

    /**
     * 액세스 토큰을 생성합니다.
     * @param id 사용자를 식별할 수 있는 아이디
     * @param roleName 사용자의 역할 이름
     * @param roleFlag 사용자의 역할 번호
     * @return 액세스 토큰
     */
    public String generateAppAccessToken(Integer id, String roleName, Integer roleFlag) {
        var claims = new HashMap<String, Serializable>();
        claims.put("id", id);
        claims.put("roleName", roleName);
        claims.put("roleFlag", roleFlag);
        return buildAccessToken(claims, null);
    }

    /**
     * 스마트팜 전용 액세스 토큰을 생성합니다.
     * @param id 스마트팜 아이디
     * @param uuid 스마트팜 uuid
     * @param roleName 스마트팜 권한 이름
     * @param roleFlag 스마트팜 권한 플래그
     * @return 액세스 토큰
     */
    public String generateFarmAccessToken(Integer id, String uuid, String roleName, Integer roleFlag) {
        var claims = new HashMap<String, Serializable>();
        claims.put("id", id);
        claims.put("uuid", uuid);
        claims.put("roleName", roleName);
        claims.put("roleFlag", roleFlag);
        return buildAccessToken(claims, null);
    }

    /**
     * 리프레쉬 토큰을 생성합니다.
     * @param id 사용자를 식별할 수 있는 식별자
     * @return 리프레쉬 토큰
     */
    public String generateRefreshToken(Integer id) {
        var claims = new HashMap<String, Serializable>();
        claims.put("id", id);
        var expiration = new Date(System.currentTimeMillis() + 1000L * 3600 * 24 * 7);
        return buildRefreshToken(claims, expiration);
    }

    /**
     * 토큰을 파싱합니다.
     * @param jwt jwt 토큰
     * @return Claims
     */
    public Claims parseToken(String jwt) {
        try {
            return Jwts.parser()
                    .verifyWith(this.PUBLIC_KEY)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch(ExpiredJwtException ex) {
            throw new ExpiredAuthorizationDataException("인증정보가 만료되었습니다.");
        } catch (MalformedJwtException | SignatureException ex) {
            throw new InvalidAuthorizationDataException("인증정보가 유효하지 않습니다.");
        }
    }

    public JwtParseResult tryParseToken(String jwt) {
        try {
            return JwtParseResult.success(parseToken(jwt));
        } catch (Exception ex) {
            return JwtParseResult.error(ex.getClass(), ex.getMessage());
        }
    }

    public Integer getId(Claims claims) {
        return claims.get("id", Integer.class);
    }
}