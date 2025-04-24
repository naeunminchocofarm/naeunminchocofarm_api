package com.naeunminchocofarm.ncf_api.serviceApply.dto;

import java.time.OffsetDateTime;

public record ServiceApplyDetailDTO(
        Integer id,
        String status,
        OffsetDateTime applicationAt,
        String memberName,
        String memberId,
        String memberEmail,
        String memberTell,
        String type,
        String contactTell,
        String content,
        String memo
) {
}
