package com.naeunminchocofarm.ncf_api.lib.auth;

public class EmptyAuthorizationFieldException extends RuntimeException {
    public EmptyAuthorizationFieldException(String message) {
        super(message);
    }
}
