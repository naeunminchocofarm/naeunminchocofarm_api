package com.naeunminchocofarm.ncf_api.lib.exception;

public class ApiErrorResponse {
    private final String code;
    private final String message;

    public ApiErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
