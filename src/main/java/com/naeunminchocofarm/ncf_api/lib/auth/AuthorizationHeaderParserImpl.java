package com.naeunminchocofarm.ncf_api.lib.auth;

import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthorizationHeaderParserImpl implements AuthorizationHeaderParser {
    private final JwtHandler JWT_HANDLER;
    private final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    public AuthorizationHeaderParserImpl(JwtHandler JWT_HANDLER) {
        this.JWT_HANDLER = JWT_HANDLER;
    }

    @Override
    public String getAuthorizationHeaderName() {
        return HttpHeaders.AUTHORIZATION;
    }

    @Override
    public Object parse(String authorizationHeader, Set<String> expectedRoleNames, Set<Integer> expectedRoleFlags) {
        if (authorizationHeader == null || !authorizationHeader.startsWith(this.AUTHORIZATION_HEADER_PREFIX)) {
            throw new EmptyAuthorizationFieldException("인증정보가 존재하지 않습니다.");
        }

        final String JWT = authorizationHeader.substring(this.AUTHORIZATION_HEADER_PREFIX.length());

        Claims claims = null;

        try {
            claims = this.JWT_HANDLER.parseToken(JWT);
        } catch(ExpiredJwtException ex) {
            throw new ExpiredAuthorizationDataException("인증정보가 만료되었습니다.");
        } catch (MalformedJwtException | SignatureException ex) {
            throw new InvalidAuthorizationDataException("인증정보가 유효하지 않습니다.");
        }

        assert claims != null;

        final Long USER_ID = claims.get("id", Long.class);
        final var ROLE_NAMES = new HashSet<>((List<String>)claims.get("roleNames", List.class));
        final var ROLE_FLAGS = new HashSet<>((List<Integer>)claims.get("roleFlags", List.class));
        final var USER = new AuthInfo(USER_ID, ROLE_NAMES, ROLE_FLAGS);

        if (
                !(expectedRoleFlags.isEmpty() || USER.containAnyRoleFlags(expectedRoleFlags)) ||
                        !(expectedRoleNames.isEmpty() || USER.containAnyRoleNames(expectedRoleNames))
        ) {
            throw new InvalidRoleException("권한이 유효하지 않습니다.");
        }

        return USER;
    }
}