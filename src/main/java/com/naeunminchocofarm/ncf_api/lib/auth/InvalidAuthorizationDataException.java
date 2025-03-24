package com.naeunminchocofarm.ncf_api.lib.auth;

public class InvalidAuthorizationDataException extends RuntimeException {
    public InvalidAuthorizationDataException(String message) {
        super(message);
    }
}
