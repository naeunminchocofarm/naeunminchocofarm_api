package com.naeunminchocofarm.ncf_api.lib.security;

public class UnauthenticatedAccessException extends RuntimeException {
    public UnauthenticatedAccessException(String message) {
        super(message);
    }
}
