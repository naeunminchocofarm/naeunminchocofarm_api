package com.naeunminchocofarm.ncf_api.lib.auth;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String message) {
        super(message);
    }
}
