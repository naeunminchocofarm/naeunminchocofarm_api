package com.naeunminchocofarm.ncf_api.lib.jwt;

import io.jsonwebtoken.Claims;

import java.util.Optional;

public record JwtParseResult(
        Optional<Claims> claims,
        Optional<Class<? extends Exception>> errorClass,
        Optional<String> errorMessage
) {
    public static JwtParseResult success(Claims claims) {
        return new JwtParseResult(Optional.of(claims), Optional.empty(), Optional.empty());
    }

    public static JwtParseResult error(Class<? extends Exception> errorClass, String errorMessage) {
        return new JwtParseResult(Optional.empty(), Optional.of(errorClass), Optional.of(errorMessage));
    }

    public boolean isSuccess() {
        return claims.isPresent();
    }
}
