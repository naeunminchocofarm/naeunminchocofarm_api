package com.naeunminchocofarm.ncf_api.lib.auth;

public class ExpiredAuthorizationDataException extends RuntimeException {
    public ExpiredAuthorizationDataException(String message) {
        super(message);
    }
}
