package com.naeunminchocofarm.ncf_api.lib.exception;

public class ExpiredAuthorizationDataException extends RuntimeException {
    public ExpiredAuthorizationDataException(String message) {
        super(message);
    }
}
