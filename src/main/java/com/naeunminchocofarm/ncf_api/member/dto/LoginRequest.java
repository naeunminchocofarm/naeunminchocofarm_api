package com.naeunminchocofarm.ncf_api.member.dto;

public record LoginRequest (
		String loginId,
		String password
) {}
