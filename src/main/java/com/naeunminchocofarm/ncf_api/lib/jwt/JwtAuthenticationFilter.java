package com.naeunminchocofarm.ncf_api.lib.jwt;

import com.naeunminchocofarm.ncf_api.lib.exception.EmptyAuthorizationFieldException;
import com.naeunminchocofarm.ncf_api.lib.exception.ExpiredAuthorizationDataException;
import com.naeunminchocofarm.ncf_api.lib.exception.InvalidAuthorizationDataException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtHandler jwtHandler;

	public JwtAuthenticationFilter(JwtHandler jwtHandler) {
		this.jwtHandler = jwtHandler;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		final String AUTHORIZATION_PREFIX = "Bearer ";

		if (header != null && header.startsWith(AUTHORIZATION_PREFIX)) {
			final String jwt = header.substring(AUTHORIZATION_PREFIX.length());
			Claims claims = this.jwtHandler.parseToken(jwt);
			var authenticationToken = getAuthenticationToken(claims);
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}

		filterChain.doFilter(request, response);
	}

	private Authentication getAuthenticationToken(Claims claims) {
		Integer userId = claims.get("id", Integer.class);
		String roleName = claims.get("roleName", String.class);
		return new UsernamePasswordAuthenticationToken(userId, null, Set.of(new SimpleGrantedAuthority(roleName)));
	}
}

