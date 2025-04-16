package com.naeunminchocofarm.ncf_api.lib.exception;

import com.naeunminchocofarm.ncf_api.lib.auth.EmptyAuthorizationFieldException;
import com.naeunminchocofarm.ncf_api.lib.auth.ExpiredAuthorizationDataException;
import com.naeunminchocofarm.ncf_api.lib.auth.InvalidAuthorizationDataException;
import com.naeunminchocofarm.ncf_api.lib.auth.InvalidRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ApiErrorResponse(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(EmptyAuthorizationFieldException.class)
    public ResponseEntity<ApiErrorResponse> handleEmptyAuthorizationFieldException(EmptyAuthorizationFieldException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorResponse("EMPTY_TOKEN", ex.getMessage()));
    }

    @ExceptionHandler(ExpiredAuthorizationDataException.class)
    public ResponseEntity<ApiErrorResponse> handleExpiredAuthorizationDataException(ExpiredAuthorizationDataException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorResponse("EXPIRED_TOKEN", ex.getMessage()));
    }

    @ExceptionHandler(InvalidAuthorizationDataException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidAuthorizationDataException(InvalidAuthorizationDataException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorResponse("INVALID_TOKEN", ex.getMessage()));
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRoleException(InvalidRoleException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorResponse("INVALID_ROLE", ex.getMessage()));
    }
}
