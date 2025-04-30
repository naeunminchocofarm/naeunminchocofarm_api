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
     * @param claims 토큰에 저장할 데이터의 키와 값
     * @return 액세스 토큰
     */
    public String generateAccessToken(Map<String, Serializable> claims) {
        var expiration = new Date(System.currentTimeMillis() + 1000L * this.EXPIRATION_SECONDS);
        return buildAccessToken(claims, expiration);
    }

    /**
     * 리프레쉬 토큰을 생성합니다.
     * @param claims 토큰에 저장할 데이터의 키와 값
     * @return 리프레쉬 토큰
     */
    public String generateRefreshToken(Map<String, Serializable> claims) {
        var expiration = new Date(System.currentTimeMillis() + 1000L * 3600 * 24 * 7);
        return buildRefreshToken(claims, expiration);
    }

    /**
     * 무기한의 액세스 토큰을 생성합니다.
     * @param claims 토큰에 저장할 데이터의 키와 값
     * @return 무기한 액세스 토큰
     */
    public String generateIndefiniteAccessToken(Map<String, Serializable> claims) {
        return buildAccessToken(claims, null);
    }

    /**
     * 무기한의 리프레쉬 토큰을 생성합니다.
     * @param claims 토큰에 저장할 데이터의 키와 값
     * @return 무기한 리프레쉬 토큰
     */
    public String generateIndefiniteRefreshToken(Map<String, Serializable> claims) {
        return buildRefreshToken(claims, null);
    }

    private String buildAccessToken(Map<String, Serializable> claims, Date expiration) {
        claims.put(TOKEN_TYPE_KEY, TOKEN_TYPE_VALUE_ACCESS);
        return buildToken(claims, expiration);
    }

    private String buildRefreshToken(Map<String, Serializable> claims, Date expiration) {
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
     * 리프레쉬 토큰을 파싱합니다.
     * @param refreshToken 리프레쉬 토큰
     * @return claims
     */
    public Claims parseRefreshToken(String refreshToken) {
        var claims = parseToken(refreshToken);
        var tokenType = claims.get(TOKEN_TYPE_KEY, String.class);
        if (!Objects.equals(tokenType, TOKEN_TYPE_VALUE_REFRESH)) {
            throw new InvalidAuthorizationDataException("리프레쉬 토큰의 인증정보가 유효하지 않습니다.");
        }
        return claims;
    }

    /**
     * 액세스 토큰을 파싱합니다.
     * @param accessToken 액세스 토큰
     * @return claims
     */
    public Claims parseAccessToken(String accessToken) {
        var claims = parseToken(accessToken);
        var tokenType = claims.get(TOKEN_TYPE_KEY, String.class);
        if (!Objects.equals(tokenType, TOKEN_TYPE_VALUE_ACCESS)) {
            throw new InvalidAuthorizationDataException("액세스 토큰의 인증정보가 유효하지 않습니다.");
        }
        return claims;
    }

    private Claims parseToken(String jwt) {
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

    public JwtParseResult tryParseAccessToken(String accessToken) {
        try {
            return JwtParseResult.success(parseAccessToken(accessToken));
        } catch (Exception ex) {
            return JwtParseResult.error(ex.getClass(), ex.getMessage());
        }
    }

    public JwtParseResult tryParseRefreshToken(String refreshToken) {
        try {
            return JwtParseResult.success(parseRefreshToken(refreshToken));
        } catch (Exception ex) {
            return JwtParseResult.error(ex.getClass(), ex.getMessage());
        }
    }
}