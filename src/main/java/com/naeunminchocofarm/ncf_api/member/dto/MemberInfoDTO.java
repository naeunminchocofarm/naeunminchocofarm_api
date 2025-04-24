package com.naeunminchocofarm.ncf_api.member.dto;

public record MemberInfoDTO(
        Integer id,
        String loginId,
        String name,
        String email,
        String tell
) {
}
