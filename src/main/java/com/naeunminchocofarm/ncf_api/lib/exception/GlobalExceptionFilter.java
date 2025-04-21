package com.naeunminchocofarm.ncf_api.lib.exception;

import com.naeunminchocofarm.ncf_api.lib.security.UnauthenticatedAccessException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class GlobalExceptionFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ApiException ex) {
            writeResponse(servletResponse, ex.getStatus(), new ApiErrorResponse(ex.getCode(), ex.getMessage()));
        } catch (EmptyAuthorizationFieldException ex) {
            writeResponse(servletResponse, HttpStatus.UNAUTHORIZED, new ApiErrorResponse("EMPTY_TOKEN", ex.getMessage()));
        } catch (ExpiredAuthorizationDataException ex) {
            writeResponse(servletResponse, HttpStatus.UNAUTHORIZED, new ApiErrorResponse("EXPIRED_TOKEN", ex.getMessage()));
        } catch (InvalidAuthorizationDataException ex) {
            writeResponse(servletResponse, HttpStatus.UNAUTHORIZED, new ApiErrorResponse("INVALID_TOKEN", ex.getMessage()));
        } catch (InvalidRoleException ex) {
            writeResponse(servletResponse, HttpStatus.FORBIDDEN, new ApiErrorResponse("INVALID_ROLE", ex.getMessage()));
        } catch (UnauthenticatedAccessException ex) {
            writeResponse(servletResponse, HttpStatus.UNAUTHORIZED, new ApiErrorResponse("UNAUTHENTICATED_ACCESS", ex.getMessage()));
        }
    }

    private void writeResponse(ServletResponse servletResponse, HttpStatus status, ApiErrorResponse apiErrorResponse) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json");
        httpResponse.setStatus(status.value());
        httpResponse.getWriter().write(apiErrorResponse.toJson());
    }
}
